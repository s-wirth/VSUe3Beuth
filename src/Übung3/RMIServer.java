// Datei: RMIServer.java
// Autor: Christian Mehns
// Thema: RMI Server und Client
// -------------------------------------------------------------
package Übung3;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Übung2.PhoneBook;


public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {
	
	//constructor
	public RMIServer() throws RemoteException{}
	
	public String search(String name, String number) throws Exception{
		System.out.println("RMI suche läuft...");	
		String results = PhoneBook.search(name, number);		
		System.out.println("Sende Daten an RMIClient");
		return results;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		try{
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			System.out.println("RMI : Registry wurde erzeugt.");
			
			RMIServer rmiServer = new RMIServer();
			
			Naming.rebind("RMIServer",rmiServer);
            System.out.println("RMI : Abteilungsserver registriert");
            System.out.println("Server wartet auf RMIs");
		}
		catch(RemoteException e){
			System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
		}		
	 }
	
	public void quit() throws RemoteException{
		System.out.println("RMI Server wird herunter gefahren");
		Registry registry = LocateRegistry.getRegistry();
        try {
            registry.unbind("RMIServer");
//            System.exit(0);
           
        } catch (Exception e) {
            throw new RemoteException("Could not unregister service, quiting anyway", e);
        }
        
	}
	

}
