package logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class MyServer extends UnicastRemoteObject implements ServerInterface {
	private List<String> clientTable = new ArrayList<String>();
	private List<Item> itemToSellTable = new ArrayList<Item>();
	private ClientInterface client;

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

	public ClientInterface getClient() {
		return client;
	}

	public void setClient(ClientInterface client) {
		this.client = client;
	}

	public void removeItemToSell(Item inputItem){
		int id = inputItem.getId();
		Iterator<Item> it = getItemToSellTable().iterator();
		Item item;
		while (it.hasNext()){
			item = it.next();
			if (item.getId()==id){
				System.out.println("dans le serveur " + item.getonSale());
				getItemToSellTable().remove(item);
				break;
			}	
		}
	}
	
	public void getItemsToSell() {

		Iterator<Item> it = getItemToSellTable().iterator();
		Item item;
		if(getItemToSellTable().size() == 0){
			System.out.println("Liste vide");
		}
		while (it.hasNext()){
			item = it.next();
			System.out.println(item.getName() + " " + item.getDescription() + " " + item.getId());
		}
	}
	
	public List<Item> getItemToSellTable() {
		return itemToSellTable;
	}
	
	public void addItemToSell(Item item){
		getItemToSellTable().add(item);
		System.out.println(this.itemToSellTable.size());
	}
	
	public void callBack(MyClient client){
		
	}

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