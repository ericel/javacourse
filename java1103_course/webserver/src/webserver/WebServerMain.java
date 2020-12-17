package webserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WebServerMain {

	/**
	 * The server listens on this port. The listening port should be 
	 * between (not inclusive of) 1024 and lest than 65535.
	 */
	private final static int LISTENING_PORT = 50500;
		protected static Socket client;
		protected static DataInputStream in;
		protected static PrintStream out;
		static String requestedFile;

	/**
	 * the main program opens a server socket and listens for connection. 
	 * @param args ignored
	 */
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket (LISTENING_PORT);
		}
		catch (Exception e) {
			System.out.println("Failed to create listening port.");
			return;
		}
		
		System.out.println("Listening on port :" + LISTENING_PORT);
		
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				System.out.println("\nConnection from "
						+ connection.getRemoteSocketAddress());
					ConnectionThread thread = new ConnectionThread(connection);
					thread.start();
					
			}
		}
		
		catch (Exception e) {
			System.out.println("Server socket shut down undexpectedly!");
			System.out.println("Error: "+ e);
			System.out.println("Exiting");
		}
	}
	
	/**
	 * Handle communication with one client. the method reads the lines of text
	 * from the client and prints it out. it will continue to read until the 
	 * connection severed, until error occurs or empty line is encountered. 
	 * this will be infinite loop listening for client requests.
	 * Exceptions are caught and handled to prevent the server shutdown. 
	 * 
	 */
		
	private static void handleConnection(Socket connection) {
		
		String httpRootDir = "/Documents and Settings";
		client = connection;
		
		try {
			// create input/output streams to communicate with client
			in = new DataInputStream(client.getInputStream());
			out = new PrintStream (client.getOutputStream());
			
			String line = null;
			String request = null;
			
			request = in.readLine();
			line = request;
			
			while (line.length()> 0) {
				line = in.readLine();
			}
			
			StringTokenizer St = new StringTokenizer (request);
			
			// check if HTTP requests "GET"
			
			if (!St.nextToken().equals("GET")) {
				
				//if it didn't send GET request send error 501
				sendErrorResponse(501);
				return;
				
			}
			//parse request to get fileName
			requestedFile = St.nextToken();
			
			//create File Object
			
			File f = new File(httpRootDir + requestedFile);
			if (!f.canRead()) {
				//if can't read seed error 404
				sendErrorResponse(404);
				return;
			}
			//send http response
			sendResponseHeader(getMimeType(requestedFile),(int)f.length());
			sendFile(f,client.getOutputStream());
		
		}
		catch (Exception e) {
			System.out.println("Error While connecting to the Client :" + e);
			
		}
		finally {// to make sure the connection is severed before closing
			try {
				connection.close();
			}
			catch(Exception e) {
				System.out.println("Connection closed");
			}
		}
	}
		
	private static void sendResponseHeader(String type,int length) 
	{
		out.println("HTTP/1.1 200 OK");
		out.println("Content type: " + type);
		out.println("Content Length: " + length);
		out.println("Connection: Close " + "\r\n");
	}
	
	private static void sendErrorResponse(int errorCode)
	{
		switch(errorCode) {
			case 404:
				out.println("Http/1.1 404 Not Found");
				out.println("Connection: Close ");
				out.println("Content type: Text/Plain" + "\r\n");
				out.println("<html><head><title>Error</title></head><body><h2>Error: "
						+ "404 Not Found</h2><p> the requested resource doesn't exist on this server.</p></body></html>");
				break;
			
			case 501:
				out.println("HTTP/1.1 501 Not Implemented");
				out.println("Connection: Closed");
				out.println("content Type: Text/plain"+ "\r\n");
				break;
			}
	}
		//extract the mime of the file:
	private static String getMimeType(String fileName) {
		
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) // no file extension in name
			return "x-application/x-unknown";
		String ext = fileName.substring(pos + 1).toLowerCase();
		if (ext.equals("txt"))
			return "text/plain";
		else if (ext.equals("html"))
			return "text/html";
		else if (ext.equals("htm"))
			return "text/html";
		else if (ext.equals("css"))
			return "text/css";
		else if (ext.equals("js"))
			return "text/javascript";
		else if (ext.equals("java"))
			return "text/x-java";
		else if (ext.equals("jpeg"))
			return "image/jpeg";
		else if (ext.equals("jpg"))
			return "image/jpeg";
		else if (ext.equals("png"))
			return "image/png";
		else if (ext.equals("gif"))
			return "image/gif";
		else if (ext.equals("ico"))
			return "image/x-icon";
		else if (ext.equals("class"))
			return "application/java-vm";
		else if (ext.equals("jar"))
			return "application/java-archive";
		else if (ext.equals("zip"))
			return "application/zip";
		else if (ext.equals("xml"))
			return "application/xml";
		else if (ext.equals("xhtml"))
			return "application/xhtml+xml";
		else
			return "x-application/x-unknown";
		// Note: x-application/x-unknown is something made up;
		// it will probably make the browser offer to save the file.
		} // end getMimeTyp
	
	private static void sendFile(File file, OutputStream socketOut) throws IOException {
		InputStream inFile = new BufferedInputStream (new FileInputStream(file));
		OutputStream outFile = new BufferedOutputStream (socketOut);
		
		while(true) {
			int num = inFile.read();
			if(num <0)
				break;
			outFile.write(num);
			
		}
		outFile.flush();
	}
	//create a thread
	private static class ConnectionThread extends Thread {
		Socket connection;
		ConnectionThread (Socket connection) {
			this.connection = connection;
		}
		public void run() {
			handleConnection(connection);
		}
	}
}