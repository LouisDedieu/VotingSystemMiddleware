package service;

import exceptions.BadCredentialsException;
import rmi.RMIClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationService extends Remote {
    void getVoteMaterial(RMIClient clientStub) throws RemoteException, BadCredentialsException;
    String generateOTP(String studentNumber) throws RemoteException;
}
