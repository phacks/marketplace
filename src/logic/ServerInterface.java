package logic;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
	void registerClient(ClientInterface client) throws RemoteException;
	public void unregisterClient(String name) throws RemoteException;
	List<ClientInterface> getClients() throws RemoteException;
	void addItemToSell(Item item) throws RemoteException;
	void removeItemToSell(Item item) throws RemoteException;
	List<Item> getItemToSellTable() throws RemoteException;
	public ClientInterface getClient() throws RemoteException;
	MyClient getOwner(String ownerItem) throws RemoteException;
}