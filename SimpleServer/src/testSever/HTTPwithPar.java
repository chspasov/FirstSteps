package testSever;

/**
 * myHTTPServer.java
 * Author: T.Spasov
 * @version 1.00
 */
import java.io.*;
import java.net.*;

public class HTTPwithPar extends Thread {
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
            HTTPwithPar server = new HTTPwithPar(connected);
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
    public HTTPwithPar(Socket client) {
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
          //  if (requestString.startsWith("GET")) {
                String requestParameters = "year=2012&name=some_name";
                requestString = "http://localhost:5000/";
               System.out.println( sendRequest(requestString, requestParameters));
                String file_name = "result.html";

                URL formAction = new URL("http://localhost:5000/?year=2012&name=some_name");
                FileWriter fw = new FileWriter(file_name);
                String xml_data = "fsdkuhfsdj";
                System.out.println(postData(new StringReader(xml_data), formAction, fw));
                while ((requestString = inFromClient.readLine()) == null)
                    ;
                System.out.println(requestString);
                while (inFromClient.ready()) {
                    // read the HTTP complete HTTP Query
                    requestString = inFromClient.readLine();
                    System.out.println(requestString);
                }
                

    //        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendRequest(String requestString, String requestParameters) {

        String result = null;
        if (requestString.startsWith("http://"))
        {
            // Send a GET request to the servlet
            try
            {

                // Send data
                String urlStr = requestString;
                if (requestParameters != null && requestParameters.length() > 0)
                {
                    urlStr += "?" + requestParameters;
               }
                URL url = new URL(urlStr);
                URLConnection conn = url.openConnection();

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null)
                {
                    sb.append(line);
                }
                rd.close();
                result = sb.toString();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
    * Reads data from the data reader and posts it to a server via POST request.
    * data - The data you want to send
    * endpoint - The server's address
    * output - writes the server's response to output
    * @throws Exception
    */
    public static String postData(Reader data, URL endpoint, Writer output) throws Exception
    {
        System.out.println("dfsln");
        HttpURLConnection urlc = null;
        try
        {
            urlc = (HttpURLConnection) endpoint.openConnection();
            try
            {
                urlc.setRequestMethod("POST");
                System.out.println(urlc.getRequestMethod());
            } catch (ProtocolException e)
            {
                throw new Exception("Shouldn't happen: HttpURLConnection doesn't support POST??", e);
            }
            urlc.setDoOutput(true);
            urlc.setDoInput(true);
            urlc.setUseCaches(false);
            urlc.setAllowUserInteraction(false);
            urlc.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");

            OutputStream out = urlc.getOutputStream();

            try
            {
                Writer writer = new OutputStreamWriter(out, "UTF-8");
                pipe(data, writer);
                writer.close();
            } catch (IOException e)
            {
                throw new Exception("IOException while posting data", e);
            } finally
            {
                if (out != null)
                    out.close();
            }

            InputStream in = urlc.getInputStream();
            try
            {
                Reader reader = new InputStreamReader(in);
                pipe(reader, output);
                reader.close();
            } catch (IOException e)
            {
                throw new Exception("IOException while reading response", e);
            } finally
            {
                if (in != null)
                    in.close();
            }

        } catch (IOException e)
        {
            throw new Exception("Connection error (is server running at " + endpoint + " ?): " + e);
        } finally
        {
            if (urlc != null)
                urlc.disconnect();
        }
        
        
        return urlc.getRequestMethod();
    }

    /**
    * Pipes everything from the reader to the writer via a buffer
    */
    private static void pipe(Reader reader, Writer writer) throws IOException
    {
        char[] buf = new char[1024];
        int read = 0;
        while ((read = reader.read(buf)) >= 0)
        {
            writer.write(buf, 0, read);
        }
        writer.flush();
    }
    
   // sendRequest(requestString requestParameters) ;
    
    

}
