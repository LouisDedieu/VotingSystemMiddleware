package impl;

import model.Candidate;
import service.VotingService;
import utils.CandidateList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    private LocalDateTime voteStartDate;
    private LocalDateTime voteEndDate;
    private boolean isVotingActive = false;

    public VotingServiceImpl(int port) throws RemoteException {
        super(port);
    }
    
    

    @Override
    public void vote(String studentNumber, Map<Integer, Integer> votes, String OTP) throws RemoteException{
        if (!isVotingActive) {
            throw new RemoteException("Le vote n'est pas actif actuellement.");
        }
    }

    @Override
    public Candidate[] getCandidates() throws RemoteException {
        return CandidateList.getCandidates();
    }

    public void startVoting() {
        isVotingActive = true;
        voteStartDate = LocalDateTime.now();
        // Vous pouvez également définir voteEndDate si vous voulez une durée fixe pour le vote
    }

    public void stopVoting() {
        isVotingActive = false;
        voteEndDate = LocalDateTime.now();
    }

}
