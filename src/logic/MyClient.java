package logic;

import gui.Window;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




@SuppressWarnings("serial")
public class MyClient extends UnicastRemoteObject implements ClientInterface {
	private String name;
	private ServerInterface server;
	private  List<Item> myItemTable = new ArrayList<Item>();

	public MyClient(String name) throws RemoteException {
		super();	
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public ServerInterface getServer() {
		return server;
	}

	public void setServer(ServerInterface server) {
		this.server = server;
	}

	public void receiveMsg (String msg) {
		System.out.println(msg);
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

	/*	public void getMyItems() {

		Iterator<Item> it = getMyItemTable().iterator();
		Item item;
		while (it.hasNext()){
			item = it.next();
		}
	}*/

	public void addItem(Item item){
		getMyItemTable().add(item);
		System.out.println(myItemTable.get(0));
	}

	public void removeItem(Item item){
		if (!getMyItemTable().contains(item)) {
			System.out.println("You don't have this item !");
		}
		getMyItemTable().remove(item);
	}

	public static void main(String args[]) throws NotBoundException, IOException {
		new Window(500,500);
	}

	public List<Item> getMyItemTable() {
		return myItemTable;
	}

	public void removeItemMarket(Item inputItem, int inputId){
		int id = inputItem.getId();
		Iterator<Item> it = getMyItemTable().iterator();
		Item item;
		while (it.hasNext()){
			item = it.next();
			if (item.getId()==id){
				System.out.println("dans le serveur " + item.getonSale());
				item.setonSale(false);
				break;
			}
		}
	}

	public void setMyItemTable(List<Item> myItemTable) {
		this.myItemTable = myItemTable;
	}
	
	public void print(){
		System.out.println(this);
	}

}