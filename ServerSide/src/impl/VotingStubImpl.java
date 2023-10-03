package impl;

import rmi.VotingStub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VotingStubImpl extends UnicastRemoteObject implements VotingStub {
    protected VotingStubImpl(int port) throws RemoteException {
        super(port);
    }

}
