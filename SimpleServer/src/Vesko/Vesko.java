package Vesko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Vesko {

	private static int port = 8000;

	/*
	 * WebServer constructor.
	 */
	protected void start() {
		ServerSocket servSocket;
		System.out.println("Webserver starting up on port " + port + " !");

		try {
			servSocket = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return;
		}

		System.out.println("Waiting for connection...");

		String cookie = "";

		for (;;) {
			try {
				Socket socket = servSocket.accept();
				System.out.println("Connection, sending data.");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream());

				String str = ".";
				String helpString = "";

				while (str.length() != 0) {
					str = in.readLine();
					System.out.println("" + str);
					if (str.startsWith("GET")) {
						helpString = str.substring(4, str.indexOf(" HTTP"));
						System.out.println("Get-Request-For:" + helpString);
					}
					if (str.startsWith("Accept-Language:")) {
						cookie = str.substring(16, str.indexOf(";"));
						cookie = (cookie + "; Expires=Sa, 01 Jan 2022 00:00:01 GMT");
						cookie = cookie.replace("bg-BG,bg", "en-US,en");
						System.out.println("Set-Cookie: " + cookie);
					}
				}

				if (helpString.equals("/index.php")) {
					out.println("HTTP/1.0 200 OK");
					out.println("Content-Type: text/html");
					out.println("Server: BlackLord Bot");
					out.println("Set-Cookie: Language=" + cookie);
					out.println("Connection: close\n");
					out.write("<H1>HTTP/1.1 200 OK</H1>");

					BufferedReader inFile = new BufferedReader(new FileReader(
							"index.php"));
					String fileLine = "";
					while ((fileLine = inFile.readLine()) != null)
						out.write("\n" + fileLine);

				} else if (helpString.isEmpty()) {
					out.println("HTTP/1.1 Error 400 Bad request");
					out.println("Content-Type: text/html");
					out.println("Server: BlackLord Bot");
					out.println("Connection: close\n");
				}

				out.flush();
				socket.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Start the application.
	 */
	public static void main(String args[]) {

		Vesko ws = new Vesko();
		ws.start();
	}
}
