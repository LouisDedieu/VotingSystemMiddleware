package service;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import rmi.RMIClient;
import rmi.VotingStub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.Provider;

public interface AuthenticationService extends Remote {
    public void authenticate(RMIClient clientStub) throws RemoteException, BadCredentialsException, HasAlreadyVotedException;
    public VotingStub getVotingMaterial(String OTP) throws RemoteException;
}
