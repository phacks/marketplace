package logic;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
	void registerClient(String obj) throws RemoteException;
	void unregisterClient(String obj) throws RemoteException;
	//void broadcastMsg(String msg) throws RemoteException;
	List<String> getClients() throws RemoteException;
}