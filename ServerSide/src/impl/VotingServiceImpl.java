package impl;

import service.VotingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    public VotingServiceImpl(int port) throws RemoteException {
        super(port);
    }

//    @Override
//    public void vote(String studentNumber, Map<Integer, Integer> votes, String OTP) {
//
//    }
}
