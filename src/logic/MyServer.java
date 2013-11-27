package logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class MyServer extends UnicastRemoteObject implements ServerInterface {
	private List<ClientInterface> clientTable = new ArrayList<ClientInterface>();
	private List<Item> itemToSellTable = new ArrayList<Item>();
	private ClientInterface clientInterface;
	private MyClient client;

	public MyServer() throws IOException, RemoteException {
		super();
		LocateRegistry.createRegistry(14000);
		String[] command = new String[]{"rmiregistry","14000"};
		Runtime.getRuntime().exec(command);
		Naming.rebind("rmi://localhost:14000/chat", this);
		// setClient((ClientInterface) client);
	}

	public List<ClientInterface> getClients() {
		return(clientTable);
	}


	public void registerClient(ClientInterface client) throws RemoteException {
		if (clientTable.contains(client)) {
			throw new RemoteException("client already registered");
		}
		clientTable.add(client);
		System.out.println(clientTable.size());
		client.print();

		System.out.println("Client " + client.getName() + " registered");
	}

	public void unregisterClient(ClientInterface client) throws RemoteException {
		
		/*Iterator<ClientInterface> it = getClients().iterator();
		ClientInterface client;
		while (it.hasNext()){
			client = it.next();
			if (client.getName().equals(name)){
				clientTable.remove(client);
				System.out.println("Client " + client.getName() + " unregistered");
				break;
			}	
		}*/
		
		if (!clientTable.contains(client))
        {
            throw new RemoteException("client not registered");
        }
        clientTable.remove(client);
        System.out.println(clientTable.size());
	}

	public ClientInterface getClient() {
		return client;
	}

	public void setClient(ClientInterface clientInterface) {
		this.clientInterface = clientInterface;
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

	public MyClient getOwner(String ownerItem) throws RemoteException{
		Iterator<ClientInterface> it = getClients().iterator();
		ClientInterface clients;
		while (it.hasNext()){
			clients = it.next();
			if (clients.getName().equals(ownerItem)){
				System.out.println("Envoi d'un message a : " + client.getName());
				break;
			}	
		}
		return client;

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