package FirstServlet;

/**
 * myHTTPServer.java
 * Author: T.Spasov
 * @version 1.00
 */
import java.io.*;
import java.net.*;

public class myHTTPServer extends Thread {
    /**
     * 
     * @param args
     * @throws Exception
     *             We accept new connections and invoke a thread on the newly
     *             created HTTPServer instance which will process the request.
     */
    public static void main(String args[]) throws Exception {

        ServerSocket Server = new ServerSocket(LISTENING_PORT, 10,
                InetAddress.getByName(SERVER_HOSTNAME));
        System.out.println("TCPServer Waiting for client on port 5000");

        while (true) {
            Socket connected = Server.accept();
            myHTTPServer server = new myHTTPServer(connected);
            server.start();
        }
    }

    // the port,host and status codes that we use from the server
    public static final int LISTENING_PORT = 5000;
    public static final String SERVER_HOSTNAME = "localhost";
    private static final int SUCCESS_REQUEST = 200;
    private static final int BAD_REQUEST = 400;

    Socket connectedClient = null;
    BufferedReader inFromClient = null;
    PrintWriter outToClient = null;
    String cookie = "";

    // BufferedReader fileReader = new BufferedReader(new FileReader("index"));

    /**
     * 
     * @param client
     *            Server constructor
     */
    public myHTTPServer(Socket client) {
        connectedClient = client;
    }

    public void start() {
        try {
            System.out.println();
            System.out
                    .println("The Client " + connectedClient.getInetAddress());
            inFromClient = new BufferedReader(new InputStreamReader(
                    connectedClient.getInputStream()));
            outToClient = new PrintWriter(connectedClient.getOutputStream());

            // in this string will save the text from request
            String requestString = ".";
            // the server can response only status code 200 and 400
            int statusCode = 0;

            // loops while the buffer is not empty
            while ((requestString = inFromClient.readLine()) == null)
                ;
         //   sendRequest(requestString);
            System.out.println(requestString);
            System.out.println(requestString);
            System.out.println(requestString);
            if (requestString.startsWith("GET")) {
                statusCode = SUCCESS_REQUEST;

                /*
                 * loops till all HTTP request parameters are received. Ready
                 * function of BufferedReader tells whether the stream is ready
                 * to be read or not, therefore we process the HTTP we get the
                 * complete request available for processing, including all
                 * request
                 */
                while (inFromClient.ready()) {
                    // read the HTTP complete HTTP Query
                    requestString = inFromClient.readLine();
                    System.out.println(requestString);
                    // the server sets a cookie based on Accept-language request
                    // headers
                    if (requestString.startsWith("Accept-Language:")) {
                        cookie = requestString.substring(16,
                                requestString.indexOf(";"));
                        cookie = (cookie + "; Expires=Sa, 16 Sept 2066 06:06:06 GMT");
                        cookie = cookie.replace("bg-BG,bg", "en-US,en");
                        System.out.println("Set-Cookie: " + cookie);
                    }

                }
            } else {
                statusCode = BAD_REQUEST;
            }

            sendResponse(statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param statusCode
     * @throws Exception
     *             send response from server to browser as the server accepts
     *             only HTTP GET requests and responds with 200 and 400 only
     */
    public void sendResponse(int statusCode) throws Exception {

        String statusLine = null;
        String serverdetails = "Server: Java HTTPServer";
        String contentTypeLine = "Content-Type: text/html";
        BufferedReader fileReader = new BufferedReader(new FileReader(
                "index.html"));
        if (statusCode == SUCCESS_REQUEST) {
            System.out.println("Status: 200");
            statusLine = "HTTP/1.1 200 OK";
            outToClient.println(statusLine);
            outToClient.println(serverdetails);
            outToClient.println(contentTypeLine);
            outToClient.println("Set-Cookie: Language=" + cookie);
            outToClient.println("Connection: close");
            outToClient.println("");
            while (fileReader.ready()) {
                try {
                    outToClient.write(fileReader.readLine());
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            fileReader.close();
            outToClient.flush();
        } else {
            System.out.println("Status: 400");
            statusLine = "HTTP/1.1 400 Not Found";
            outToClient.println(statusLine);
            outToClient.println(serverdetails);
            outToClient.println("Connection: close");
            outToClient.println("");
        }

        outToClient.flush();
        outToClient.close();
        connectedClient.close();
    }

//    public void sendRequest(String request) throws Exception
//    {
//        // i.e.: request = "http://example.com/index.php?param1=a&param2=b&param3=c";
//        URL url = new URL(request);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setDoOutput(true);
//        connection.setInstanceFollowRedirects(false);
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Content-Type", "text/plain");
//        connection.setRequestProperty("charset", "utf-8");
//        connection.connect();
//    }

}