package rmi;

import model.candidate.Candidate;
import model.user.User;
import model.vote.Vote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface RMIClient extends Remote {
    String getStudentNumber() throws RemoteException;
    String getPassword() throws RemoteException;

    String askStudentNumber() throws RemoteException;

    String askPassword() throws RemoteException;

    User getUser() throws RemoteException;
    void displayMessage(String message) throws RemoteException;
    void setUser(User user) throws RemoteException;
    Map<Integer, Vote> getVotes(Candidate[] candidates) throws RemoteException;
    String getOTP() throws RemoteException;
    String askForVoterName() throws RemoteException;
}
