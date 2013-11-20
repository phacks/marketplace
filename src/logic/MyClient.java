package logic;

import gui.Window;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class MyClient extends UnicastRemoteObject implements ClientInterface {
	private String name;
	private ServerInterface server;

	public MyClient() throws RemoteException {
		super();
		//this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void receiveMsg (String msg) {
		System.out.println(msg);
		System.out.println("CACA");
	}

	public static void main(String args[]) throws NotBoundException, IOException {
		
		new Window(500,500);
		
		//String myId = args[0];
		//MyClient me = new MyClient(myId);

		//ServerInterface server = (ServerInterface)Naming.lookup("rmi://130.229.178.105:14000/chat");
		/*server.registerClient(me);
		server.broadcastMsg(me.getName() + " : Hi guys, I am new ....");    

		List<ClientInterface> clients = server.getClients();
		if (!clients.isEmpty()) {
			System.out.println("\nRegistered Clients ***********");
			for (ClientInterface client : clients) {
				System.out.println(client.getName());
			}
			System.out.println("******************************\n");
		}

		for (int i=0; i<5; i++) {
			server.broadcastMsg(me.getName() + " : [" + i + "]");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

		server.broadcastMsg("good bye ...");
		server.unregisterClient(me);*/
	}

	public boolean connectTo(String inputIP, String inputPort) {
		
		try {
			setServer((ServerInterface) Naming.lookup("rmi://" + inputIP + ":" + inputPort + "/chat"));
			System.out.println("Connection au serveur OK");
			return true;
		} catch (MalformedURLException | RemoteException | NotBoundException e ) {
			return false;
		}
		
		
	}

	public ServerInterface getServer() {
		return server;
	}

	public void setServer(ServerInterface server) {
		this.server = server;
	}

}