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

        Map<Integer, Vote> votes = clientStub.getVotes(CandidateList.getCandidates());
        clientStub.displayMessage("Vous avez voté pour :");
        for (Map.Entry<Integer, Vote> vote : votes.entrySet()) {
            clientStub.displayMessage("Candidate" + vote.getValue().getCandidateRank() + " avec la note " + vote.getValue().getScore());
        }

        // Log du vote

        String voterName = clientStub.askForVoterName(); //by calling back a method on the client stub)
        VoteLog voteLog = new VoteLog(clientStub.getStudentNumber(), voterName, new Date(), votes);

        voteLogs.add(voteLog); // Ajouter le log de vote à la liste

        OTPGeneratedForCurrentUser.markAsUsed(); // Marquer l'OTP comme utilisé

        //Affichage des résultats côté server
        System.out.println("Résultats du vote :");
        resultatvote();
        clientStub.displayMessage("Merci d'avoir voté");
    }

    @Override
    public Candidate[] getCandidates() throws RemoteException {
        return CandidateList.getCandidates();
    }

    public void startVoting() {
        isVotingActive = true;
        voteStartDate = LocalDateTime.now();
        System.out.println("Vote démarré.");
        // Vous pouvez également définir voteEndDate si vous voulez une durée fixe pour le vote
    }

    public void stopVoting() {
        isVotingActive = false;
        voteEndDate = LocalDateTime.now();
        System.out.println("Vote terminé.");
        System.out.println("Résultats finaux du vote :");
        resultatvote();
        System.out.println("VotesLogs : " + voteLogs);

    }

    private void resultatvote() {
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName() + " : " + sumVotes(candidate));
        }
    }

    private float sumVotes(Candidate candidate) {
        float sum = 0;
        for (VoteLog voteLog : voteLogs) {
            for (Map.Entry<Integer, Vote> vote : voteLog.getVotes().entrySet()) {
                if (vote.getValue().getCandidateRank() == candidate.getRank()) {
                    sum += vote.getValue().getScore();
                }
            }
        }
        //retourne la moyenne
        return sum/voteLogs.size();
    }

    public void handleStart() {
        startVoting();
    }

    public void handleStop() {
        stopVoting();
    }

    public void handleExit() {
        System.out.println("Fermeture du serveur.");
        System.exit(0);
    }

    public void handleUnknownCommand() {
        System.out.println("Commande non reconnue.");
    }
}
