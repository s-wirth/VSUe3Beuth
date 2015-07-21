package Test;

import java.net.*;
import java.util.Scanner;
import java.io.*;


public class MulClient {
	private static int port = 3141;
	private static String host = "localhost";
	
	public static void main( String[] args )
	  {
	    Socket server = null;
	    
	    Scanner scanner = new Scanner(System.in);	    
	    System.out.println("Enter first factor: ");
	    int factor1 = Integer.parseInt (scanner.nextLine());
	    
	    System.out.println("Enter second factor: ");
	    int factor2 = Integer.parseInt (scanner.nextLine());
	    

	    try
	    {
	      server = new Socket( host, port );
	      Scanner     in  = new Scanner( server.getInputStream() );
	      PrintWriter out = new PrintWriter( server.getOutputStream(), true );

	      out.println( factor1 );
	      out.println( factor2 );
	      
	      System.out.println("Ergebnis: ");
	      System.out.println( in.nextLine() );

	    }
	    catch ( UnknownHostException e ) {
	      e.printStackTrace();
	    }
	    catch ( IOException e ) {
	      e.printStackTrace();
	    }
	    finally {
	      if ( server != null )
	        try { server.close(); } catch ( IOException e ) { }
	    }
	  }


}
