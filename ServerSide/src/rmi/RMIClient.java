package rmi;

import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClient extends Remote {
    public String getStudentNumber() throws RemoteException;
    public String getPassword() throws RemoteException;
    public User getUser() throws RemoteException;
    public void displayMessage(String message) throws RemoteException;
    public void setUser(User user) throws RemoteException;

}
