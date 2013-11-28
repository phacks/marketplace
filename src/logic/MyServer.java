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
	private List<ClientInterface> clientTable = new ArrayList<ClientInterface>();
	private List<Item> itemToSellTable = new ArrayList<Item>();
	private List<WishInterface> wishTable = new ArrayList<WishInterface>();
	private ClientInterface clientInterface;
	private WishInterface wishInterface;
	private MyClient client;
	private boolean connectedToBank = false;
	private BankInterface bank;

	public MyServer() throws IOException, RemoteException {
		super();
		LocateRegistry.createRegistry(14000);
		String[] command = new String[]{"rmiregistry","14000"};
		Runtime.getRuntime().exec(command);
		Naming.rebind("rmi://localhost:14000/chat", this);
	}

	public List<ClientInterface> getClients() {
		return(clientTable);
	}

	public void registerClient(ClientInterface client) throws RemoteException {
		if (clientTable.contains(client)) {
			throw new RemoteException("client already registered");
		}
		clientTable.add(client);
		if(!connectedToBank){
			setBank(client.getBank());
			connectedToBank = true;
		}
		System.out.println("Client " + client.getName() + " registered");
		System.out.println(getBank().checkAccount(client));
	}

	private BankInterface getBank() {
		return bank;
	}

	public void setBank(BankInterface bank) {
		this.bank = bank;
	}

	public void unregisterClient(ClientInterface client) throws RemoteException {
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

	public boolean callBack(ClientInterface buyer, ClientInterface owner, Item item) throws RemoteException{

		if(bank.checkAccount(buyer) < Integer.parseInt(item.getPrice())){
			buyer.tooExpensive();
			return false;
		}
		else{
			Iterator<ClientInterface> it = getClients().iterator();
			ClientInterface clients = null;
			while (it.hasNext()){
				clients = it.next();
				int id = item.getId();
				if (clients.equals(owner)){
					bank.creditAccount(clients, Integer.parseInt(item.getPrice()));
					bank.debitAccount(buyer, Integer.parseInt(item.getPrice()));
					clients.itemSold();
					clients.removeItemSold(item,id);
					return true;
				}
			}	
		}
		return false;
	}

	public void addWish(WishInterface wish) throws RemoteException{
		if (wishTable.contains(wish)) {
			throw new RemoteException("You have already made this wish");
		}
		wishTable.add(wish);
		System.out.println(wish.getNameItem() + " " + wish.getPriceItem() + " " + wish.getWisher());
	}

	public void removeWish(WishInterface wish) throws RemoteException {
		if (!wishTable.contains(wish)){
			System.out.println("You don't have this wish");
		}
		wishTable.remove(wish);
	}

	public List<WishInterface> getWishTable() {
		return wishTable;
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