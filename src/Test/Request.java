package Test;

import java.io.*;      // Fuer den Reader
import java.net.*;     // Fuer den Socket
import java.util.StringTokenizer;

class Request {
  public static void main(String[] args) throws Exception {

    // Vereinbarungen
    // ---------------------------------------------------------
    ServerSocket ss       = null;  // Fuer das accept()
    Socket cs             = null;  // Fuer die Requests
    InputStream is        = null;  // Aus dem Socket lesen
    InputStreamReader isr = null;
    BufferedReader br     = null;
    OutputStream os       = null;  // In den Socket schreiben
    PrintWriter pw        = null;
    String requestMessageLine          = null;  // Eine Zeile aus dem Socket
    String host           = null;  // Der Hostname
    int port              = 0;     // Der lokale Port
    boolean active = true;

    // Programmstart und Portbelegung
    // ---------------------------------------------------------
    host = InetAddress.getLocalHost().getHostName();
    port = 9876;
    System.out.println("Server startet auf "+host+" an Port"+port);

    // ServerSocket einrichten und in einer Schleife auf 
    // Requests warten.
    // ---------------------------------------------------------
    ss = new ServerSocket(port);
    while(active) {
      System.out.println("Warte im accept()");
      cs = ss.accept();               // <== Auf Requests warten

     
      
      // Den Request lesen (Hier nur erste Zeile)
      // -------------------------------------------------------
      is    = cs.getInputStream();
      isr   = new InputStreamReader(is);
      br    = new BufferedReader(isr);
      requestMessageLine = br.readLine();
      System.out.println("Kontrollausgabe: "+requestMessageLine);
      
      StringTokenizer tokenizer = new StringTokenizer(requestMessageLine);
      String method = tokenizer.nextToken();
      String query = tokenizer.nextToken();		
      
      // Favicon-Requests nicht bearbeiten
      // -------------------------------------------------------
      if(query.startsWith("/favicon")) {
        System.out.println("Favicon-Request");
        br.close();
        continue;                       // Zum naechsten Request
      }
      
      
      if (method.equals("GET") && query.contains("?")) { 
    	  System.out.println("Search Request");
    	  StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine, "&");
    	  
    	  String A = tokenizedLine.nextToken();
    	  A = A.substring(A.indexOf("=")+1);
    	  
    	  String B = tokenizedLine.nextToken();
    	  B = B.substring(B.indexOf("=")+1);
    	  
    	  String C = tokenizedLine.nextToken();
    	  C = C.substring(C.indexOf("=")+1, C.indexOf(" "));
    	  
    	  
    	  System.out.println("A: "+A);
    	  System.out.println("B: "+B);
    	  System.out.println("C: "+C);
    	  
    	  // shutdown Server when user presses button quit
    	  if(C.equals("quit")){
    		  active = false;
    		  System.out.println("Shutdown Server");
    	  }
    	  
    	  
    	  if(A.isEmpty() || B.isEmpty()){
    		  System.out.println("Leere Eingaben sind Ungültig!");
    	  }
    	  
    	  
    	  
    	  
//    	  String A = query.substring(str.indexOf("A=") + 1, query.indexOf("]"))
//    	  String A = query.split(arg0);
    	  
//	      while(tokenizedLine.hasMoreTokens()) {
//	    	  String A = tokenizedLine.nextToken();
//	    	  A = A.substring(A.indexOf("="));
//	    	  
//	    	  String val = tokenizedLine.nextToken();
//	    	  System.out.println(key + "\t" + val);
//	      }
    	  
      }
      
      
      else{
    	  // send formular
          // -------------------------------------------------------
          System.out.println("Request wird bearbeitet");
          os = cs.getOutputStream();
          pw  = new PrintWriter(os, true);
          
          // read textfile for html form
          BufferedReader in = new BufferedReader(new FileReader("form.txt"));
          String line = null;
          while ((line = in.readLine()) != null) {
        	  pw.println(line);
          }
          //close all streams
          pw.close();
          in.close();
          br.close();
      }
      
      
      

      
      
      

      

      
      
    }  // end while

  }  // end main()
  
  
//  private void sendFormular(){
//	// Den Request bearbeiten (Hier: nur zuruecksenden)
//      // -------------------------------------------------------
//      System.out.println("Request wird bearbeitet");
//      os = cs.getOutputStream();
//      pw  = new PrintWriter(os, true);
//      
//      // read textfile for html form
//      BufferedReader in = new BufferedReader(new FileReader("form.txt"));
//      String line = null;
//      while ((line = in.readLine()) != null) {
//    	  pw.println(line);
//      }
//      //close all streams
//      pw.close();
//  }
  
  
  
}  // end class
