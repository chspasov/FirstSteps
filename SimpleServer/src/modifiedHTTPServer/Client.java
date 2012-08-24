package modifiedHTTPServer;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 7612);
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketWriter = new PrintWriter(socket.getOutputStream());
        
        socketWriter.println("GET /view?name=ivan&year=1991 HTTP/1.1");
        socketWriter.println("Content-Type: text/html; charset=utf-8");
        socketWriter.println("Connection: close\n");
        
        while (true) {
            String msg = socketReader.readLine(); // client receives from localhost server
            System.out.println(msg); //prints the message from the server to the console
        }
        

    }

}
