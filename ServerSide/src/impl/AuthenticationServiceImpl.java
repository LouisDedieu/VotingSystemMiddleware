package impl;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import rmi.RMIClient;
import rmi.VotingStub;
import service.AuthenticationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    public AuthenticationServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void authenticate(RMIClient clientStub) throws BadCredentialsException, HasAlreadyVotedException {
    }

    @Override
    public VotingStub getVotingMaterial(String OTP) throws BadCredentialsException, HasAlreadyVotedException {
        return null;
    }
}
