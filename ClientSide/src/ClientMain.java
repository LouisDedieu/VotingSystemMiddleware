import impl.RMIClientImpl;
import model.Candidate;
import rmi.VotingStub;
import service.AuthenticationService;
import service.VotingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

import static utils.RMIUtils.*;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
            AuthenticationService authService = (AuthenticationService) registry.lookup(AUTHENTICATION_SERVICE_NAME);
            VotingService votingService = (VotingService) registry.lookup(VOTING_SERVICE_NAME);

            // Récupérez et affichez la liste des candidats
            Candidate[] candidates = votingService.getCandidates();
            System.out.println("Liste des candidats :");
            for (Candidate candidate : candidates) {
                System.out.println(candidate.toString());
            }

            RMIClientImpl clientStub = new RMIClientImpl();
            authService.authenticate(clientStub); // L'utilisateur est authentifié et reçoit un OTP

            String studentnumber = clientStub.getStudentNumber(); // L'utilisateur entre son numéro d'étudiant
            String userOTP = clientStub.getOTP(); // L'utilisateur entre son OTP

            // Obtenez le matériel de vote en fournissant l'OTP
            VotingStub votingMaterial = authService.getVotingMaterial(userOTP); // Cette méthode doit gérer l'exception HasAlreadyVotedException

            // Obtention et soumission des votes
            //Map<Candidate, Integer> votes = getUserVotes(candidates);
            votingService.vote(clientStub);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
