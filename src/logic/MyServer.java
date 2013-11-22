package logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MyServer extends UnicastRemoteObject implements ServerInterface {
	private List<String> clientTable = new ArrayList<String>();

	public MyServer() throws IOException {
		super();
		LocateRegistry.createRegistry(14000);
		String[] command = new String[]{"rmiregistry","14000"};
		Runtime.getRuntime().exec(command);
		Naming.rebind("rmi://localhost:14000/chat", this);
	}

	public List<String> getClients() {
		return(clientTable);
	}

	public void registerClient(String client) throws RemoteException {
		if (clientTable.contains(client)) {
			throw new RemoteException("client already registered");
		}
		clientTable.add(client);
		System.out.println("Client " + client + " registered");
	}

	public void unregisterClient(String client) throws RemoteException {
		if (!clientTable.contains(client)) {
			throw new RemoteException("client not registered");
		}
		clientTable.remove(client);
		System.out.println("Client " + client + " unregistered");
	}

	/*public void broadcastMsg(String msg) throws RemoteException {
		for (String client : clientTable) {
			try {
				client.receiveMsg(msg);
			} catch(RemoteException re) {
				re.printStackTrace();
			} 
		}
	}*/

	public static void main(String[] args) throws IOException {
		try {
			new MyServer();
			System.out.println("creation d'un server ok");
		} catch (RemoteException re) {
			System.out.println(re);
			System.exit(1);
		} catch (MalformedURLException me) {
			System.out.println(me);
			System.exit(1);
		}
	}

}