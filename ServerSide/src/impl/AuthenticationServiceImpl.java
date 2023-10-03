package impl;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import model.User;
import rmi.RMIClient;
import rmi.VotingStub;
import service.AuthenticationService;
import utils.UsersList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    public AuthenticationServiceImpl(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void authenticate(RMIClient clientStub) throws RemoteException, BadCredentialsException, HasAlreadyVotedException {
        UsersList usersList = new UsersList();
        String studentNumber = clientStub.getStudentNumber();
        String password = clientStub.getPassword();
        if (usersList.isValidUser(clientStub.getStudentNumber(), clientStub.getPassword())) {
            clientStub.displayMessage("You are successfully authenticated!");
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    @Override
    public VotingStub getVotingMaterial(String OTP) throws RemoteException{
        return null;
    }
}
