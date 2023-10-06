package impl;

import model.candidate.Candidate;
import model.user.OTP;
import model.vote.Vote;
import model.vote.VoteLog;
import rmi.RMIClient;
import service.VotingService;
import utils.list.CandidateList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static utils.list.VoteLogList.getVoteLogs;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    private LocalDateTime voteStartDate;
    private LocalDateTime voteEndDate;
    private boolean isVotingActive = false;
    private final AuthenticationServiceImpl authService;
    private final Candidate[] candidates;
    private final List<VoteLog> voteLogs;

    public VotingServiceImpl(int port, AuthenticationServiceImpl authService) throws RemoteException {
        super(port);
        this.authService = authService;
        candidates = CandidateList.getCandidates();
        voteLogs = getVoteLogs();
    }

    @Override
    public void vote(RMIClient clientStub) throws RemoteException{
        if (!isVotingActive) {
            clientStub.displayMessage("Le vote n'est pas encore commencé");
            return;
        }
        if (voteEndDate != null && LocalDateTime.now().isAfter(voteEndDate)) {
            clientStub.displayMessage("Le vote est terminé");
            return;
        }

        //Apres s'être connecté, on demande a l'utilisateur d'entrer son OTP pour pouvoir voter
        String OTPEntered = clientStub.getOTP();
        OTP OTPGeneratedForCurrentUser = authService.getOtpsList().getOTP(clientStub.getStudentNumber());

        if (!OTPGeneratedForCurrentUser.equals(OTPEntered)) {
            clientStub.displayMessage("OTP incorrect");
            vote(clientStub);
        }
        if (OTPGeneratedForCurrentUser.isUsed()) {
            clientStub.displayMessage("Vous avez déjà voté");
            return;
        }

        Map<Integer, Vote> votes = clientStub.getVotes(CandidateList.getCandidates());
        clientStub.displayMessage("Vous avez voté pour :");
        for (Map.Entry<Integer, Vote> vote : votes.entrySet()) {
            clientStub.displayMessage("Candidat " + vote.getKey() + " : " + vote.getValue());
        }

        // Log du vote

        String voterName = clientStub.askForVoterName(); //by calling back a method on the client stub)
        VoteLog voteLog = new VoteLog(clientStub.getStudentNumber(), voterName, new Date(), votes);

        voteLogs.add(voteLog); // Ajouter le log de vote à la liste

        OTPGeneratedForCurrentUser.markAsUsed();
        clientStub.displayMessage("Merci d'avoir voté");
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

    public void handleStart() {
        startVoting();
        System.out.println("Vote démarré.");
    }

    public void handleStop() {
        stopVoting();
        System.out.println("Vote arrêté.");
    }

    public void handleExit() {
        System.out.println("Fermeture du serveur.");
        System.exit(0);
    }

    public void handleUnknownCommand() {
        System.out.println("Commande non reconnue.");
    }
}
