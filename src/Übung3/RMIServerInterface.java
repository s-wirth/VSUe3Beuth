// Datei: RMIServerInterface.java
// Autor: Christian Mehns
// Thema: RMI Server und Client
// -------------------------------------------------------------
package Übung3;
import java.rmi.*;

public interface RMIServerInterface extends Remote {
	
	public String search(String name, String number) throws Exception;
	
	public void quit() throws RemoteException;
}
