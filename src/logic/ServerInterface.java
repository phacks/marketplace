package logic;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
	void registerClient(ClientInterface client) throws RemoteException;
	public void unregisterClient(ClientInterface client) throws RemoteException;
	List<ClientInterface> getClients() throws RemoteException;
	void addItemToSell(Item item) throws RemoteException;
	void removeItemToSell(Item item) throws RemoteException;
	List<Item> getItemToSellTable() throws RemoteException;
	public ClientInterface getClient() throws RemoteException;
<<<<<<< HEAD
	//ClientInterface getOwner(String ownerItem) throws RemoteException;
	public void callBack(String owner, Item item) throws RemoteException;
=======
	ClientInterface getOwner(String ownerItem) throws RemoteException;
	public void callBack(ClientInterface owner) throws RemoteException;
	void setBank(BankInterface bank) throws RemoteException;
>>>>>>> f85bb9089d34517c6bb64ae7c63981b944293274
}