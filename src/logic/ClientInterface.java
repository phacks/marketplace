package logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	void receiveMsg (String msg) throws RemoteException;
	String getName() throws RemoteException;
}