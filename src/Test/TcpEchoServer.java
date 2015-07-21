package Test;

import java.io.*;
import java.net.*;

class TcpEchoServer {
  public static void main(String[] args) throws Exception {
    int serverPort = 9021;
    System.out.println("S: Start auf Host sun65" +
                       " am Port "+serverPort);

    // ServerSocket einrichten und im accept() warten
    // ---------------------------------------------------------
    ServerSocket ss = new ServerSocket(serverPort);
    Socket        s = null;
    System.out.println("S: Vor dem accept()");
    s = ss.accept();

    // Leser und Schreiber einrichten
    // ---------------------------------------------------------
    BufferedReader sbr = new BufferedReader(
                           new InputStreamReader(
                             s.getInputStream()));
    PrintWriter spw = new PrintWriter(s.getOutputStream(),true);

    // Die wiederholten Echos mit diesem Client abwickeln
    // ---------------------------------------------------------
    String zeile;
    while(true) {
      System.out.println("S: Vor dem readLine()");
      zeile = sbr.readLine();
      System.out.println("S: Aus dem Socket kommt->" + zeile);
      spw.println(zeile);
//      spw.flush();
      if(zeile.equals("quit")) break;
    } // while

    spw.close();
    sbr.close();
    ss.close();
    s.close();

 } // main()
} // class
