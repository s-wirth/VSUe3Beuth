package Test;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class MulServer {
	private static int port = 3141;
	private static boolean active = true;
	
	private static void handleConnection(Socket client) throws IOException {
		System.out.println("Server started ");
		Scanner in = new Scanner(client.getInputStream());
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		
//		String factor1 = in.nextLine();
//		String factor2 = in.nextLine();		
//		out.println( new BigInteger(factor1).multiply( new BigInteger(factor2) ) );	
		
		System.out.println("Shutdown Server");
		active = false;
	}
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(port);
		
		
		while (active){
			Socket client = null;
			
			try {
				client = server.accept();
				handleConnection(client);
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if (client != null){
					try { client.close();}
					catch (IOException e){}
				}
			}
		}
		
	}

}
