package service;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import model.User;
import rmi.RMIClient;
import rmi.VotingStub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.Provider;

public interface AuthenticationService extends Remote {
    void authenticate(RMIClient clientStub) throws RemoteException, BadCredentialsException;
    VotingStub getVotingMaterial(String otp) throws RemoteException, HasAlreadyVotedException;
    String generateOTP(String studentNumber) throws RemoteException;
}
