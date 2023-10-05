package service;

import model.Candidate;
import utils.CandidateList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface VotingService extends Remote {
    public void vote(String studentNumber, Map<Integer, Integer> votes, String OTP) throws RemoteException;
    Candidate[] getCandidates() throws RemoteException;
}
