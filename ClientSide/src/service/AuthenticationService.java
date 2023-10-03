package service;

import model.Candidate;
import rmi.RMIClient;
import rmi.VotingStub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

//Définit les opérations que le serveur peut demander au client, comme obtenir le numéro d'étudiant ou afficher les candidats.
public interface AuthenticationService extends Remote {
    public void authenticate(RMIClient clientStub) throws RemoteException;
    public VotingStub getVotingMaterial(String OTP) throws RemoteException;
}
