package impl;

import model.candidate.Candidate;
import model.user.OTP;
import rmi.RMIClient;
import service.VotingService;
import utils.list.CandidateList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Map;

public class VotingServiceImpl extends UnicastRemoteObject implements VotingService {
    private LocalDateTime voteStartDate;
    private LocalDateTime voteEndDate;
    private boolean isVotingActive = false;
    private final AuthenticationServiceImpl authService;

    public VotingServiceImpl(int port, AuthenticationServiceImpl authService) throws RemoteException {
        super(port);
        this.authService = authService;
    }

    @Override
    public void vote(RMIClient clientStub) throws RemoteException, InterruptedException {
        if (!isVotingActive) {
            clientStub.displayMessage("Le vote n'est pas encore commencé");
            Thread.sleep(5000); // Attend 5 secondes
            vote(clientStub);
        }
        if (voteEndDate != null && LocalDateTime.now().isAfter(voteEndDate)) {
            clientStub.displayMessage("Le vote est terminé");
            return;
        }

        //Apres s'être connecté, on demande a l'utilisateur d'entrer son OTP pour pouvoir voter
        String OTPEntered = clientStub.getOTP();
        OTP OTPGeneratedForCurrentUser = authService.getOtpsList().getOTP(clientStub.getStudentNumber());

        if (!OTPGeneratedForCurrentUser.getOtpValue().equals(OTPEntered)) {
            clientStub.displayMessage("OTP incorrect");
            vote(clientStub);
        }
        if (OTPGeneratedForCurrentUser.isUsed()) {
            clientStub.displayMessage("Vous avez déjà voté");
            return;
        }

        Map<Integer, Integer> votes = clientStub.getVotes(CandidateList.getCandidates());
        clientStub.displayMessage("Vous avez voté pour :");
        for (Map.Entry<Integer, Integer> vote : votes.entrySet()) {
            clientStub.displayMessage("Candidat " + vote.getKey() + " : " + vote.getValue());
        }
        OTPGeneratedForCurrentUser.markAsUsed();
        clientStub.displayMessage("Merci d'avoir voté");
        return;
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
