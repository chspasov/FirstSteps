package modifiedHTTPServer;


import java.net.*;
import java.io.*;
import java.util.HashMap;
import com.google.gson.Gson;

/**
 * @author stoyanstoyanov
 *  Server that works only with GET and POST requests. If a GET method with parameters is received the server will send a empty JSON Object.
 *  If the GET method has no parameters the server will send an error message.
 *  If a request with POST method is received the server will take the JSON object from the body of the request, put more data in it and send it back to the client.
 */
public class Server extends Thread {
    
    

    private static int LISTENING_PORT = 7612; // server will listen on port 7612
    //header declaration
    private static String headerContent = "Content-Type: text/html; charset=utf-8";
    private static String headerConnection = "Connection: close";

    private static String requestInfo; //variable that stores a http request line by line

    private static HashMap<String, String> params = new HashMap<String, String>(); //hashmap for the name and year parameters

    /**
     * @param out
     * @param error
     * sends a response to the client with an user defined error message
     */
    public static void sendBadResponse(PrintWriter out, String error) {
        out.println("HTTP/1.1 400 " + error);
        out.println(headerContent);
        out.println(headerConnection);
        out.println();
        out.flush();
    }

    /**
     * @param requestInfo
     * @param in
     * reads an incomming request
     */
    public static void readRequestInfo(String requestInfo, BufferedReader in) {

        while (!requestInfo.isEmpty()) {
            try {
                requestInfo = in.readLine(); // reads the request line by line
                System.out.println(requestInfo);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * @param request
     * takes the first line of a http GET request, takes the parameters from the uri and puts it in a hashmap
     */
    public static void getParams(String request) {
        //example: /view?year=1234&name=Ellie_Goulding
        int question = request.indexOf("?") + 1;
        int paramEnd = request.indexOf(" ", question);
        String[] parameters = request.substring(question, paramEnd).split("&");
        String[] pair;
        for (int i = 0; i < parameters.length; i++) {
            pair = parameters[i].split("=");
            params.put(pair[0], pair[1]);
            //System.out.println(pair[0] + " | " + pair[1]);
        }
    }

    /**
     * @param args
     * @throws IOException
     * processes the GET and POST requests and sends the appropriate response
     */
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(LISTENING_PORT, 1, InetAddress.getByName("127.0.0.1"));
        Socket socket = null;
        BufferedReader input = null;
        PrintWriter output = null;

        while (true) {

            socket = server.accept(); // server waits for a connection from a client
            input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // input stream
            output = new PrintWriter(socket.getOutputStream()); // output stream
            Gson gson = new Gson();
            
            try {
                requestInfo = input.readLine();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (requestInfo == null) {
                socket.close();
                continue;
            }

            if (requestInfo.startsWith("GET")) {

                System.out.println(requestInfo); //prints the first line of the GET request info  
                getParams(requestInfo);

                if (params.containsKey("year") && params.containsKey("name")) {
                    String year = params.get("year");
                    String name = params.get("name");
                    Compilation compilation = new Compilation(name, year);
                    gson.toJson(compilation.toString());
                    //System.out.println(gson.toString());
                    
                }
                else {
                    sendBadResponse(output, "Request Error: No params in GET method!");
                }
                readRequestInfo(requestInfo, input);

            }
            else if (requestInfo.startsWith("POST")) {

                System.out.println(requestInfo); //prints the first line of the POST request info
                readRequestInfo(requestInfo, input);
            }
            else {
                sendBadResponse(output, "Not a POST or a GET method");
            }

            input.close();
            output.close();
            socket.close();
        }

    }
}

