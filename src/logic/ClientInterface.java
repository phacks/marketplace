package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInterface extends Remote {
	void receiveMsg (String msg) throws RemoteException;
	String getName() throws RemoteException;
	public ServerInterface getServer() throws RemoteException;
	void removeItem(Item item) throws RemoteException;
	//void addItem(Item item);
	List<Item> getMyItemTable() throws RemoteException;
	//void removeItemMarket(Item item) throws RemoteException;
	public void setName(String name) throws RemoteException;
	boolean itemSold() throws RemoteException;
	void removeItemSold(Item item,int id) throws RemoteException;
}