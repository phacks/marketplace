package logic;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
	void registerClient(String obj) throws RemoteException;
	void unregisterClient(ClientInterface obj) throws RemoteException;
	//void broadcastMsg(String msg) throws RemoteException;
	List<String> getClients() throws RemoteException;
}