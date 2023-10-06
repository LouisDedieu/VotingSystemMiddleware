package service;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import rmi.RMIClient;
import rmi.VotingStub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    void authenticate(RMIClient clientStub) throws RemoteException, BadCredentialsException;
    String generateOTP(String studentNumber) throws RemoteException;
}
