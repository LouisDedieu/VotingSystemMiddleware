package rmi;

import model.Candidate;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface RMIClient extends Remote {
    String getStudentNumber() throws RemoteException;
    String getPassword() throws RemoteException;
    User getUser() throws RemoteException;
    void displayMessage(String message) throws RemoteException;
    void setUser(User user) throws RemoteException;
    Map<Integer, Integer> getVotes(Candidate[] candidates) throws RemoteException;
    String getOTP() throws RemoteException;
}
