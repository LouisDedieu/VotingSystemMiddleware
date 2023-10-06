package service;

import model.Candidate;
import rmi.RMIClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface VotingService extends Remote {
    public void vote(RMIClient clientStub) throws RemoteException, InterruptedException;
    Candidate[] getCandidates() throws RemoteException;
}
