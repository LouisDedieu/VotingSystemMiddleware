package impl;

import service.VotingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    public VotingServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void vote(String studentNumber, Map<Integer, Integer> votes, String OTP) {

    }
}
