package logic;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
	void registerClient(String obj) throws RemoteException;
	void unregisterClient(String obj) throws RemoteException;
	//void broadcastMsg(String msg) throws RemoteException;
	List<String> getClients() throws RemoteException;
	//void addItemToSell(String s);
	void addItemToSell(Item item) throws RemoteException;
	void removeItemToSell(Item item) throws RemoteException;
	List<Item> getItemToSellTable() throws RemoteException;
	//void callBack(MyClient owner) throws RemoteException;
}