package service;

import model.candidate.Candidate;
import rmi.RMIClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VotingService extends Remote {
    public void vote(RMIClient clientStub) throws RemoteException, InterruptedException;
    Candidate[] getCandidates() throws RemoteException;
}
