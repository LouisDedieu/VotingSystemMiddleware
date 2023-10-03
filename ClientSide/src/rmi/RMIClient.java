package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClient extends Remote {
    public String getStudentNumber() throws RemoteException;
    public String getPassword() throws RemoteException;
    public void displayMessage(String message) throws RemoteException;

}
