import impl.RMIClientImpl;
import model.candidate.Candidate;
import service.AuthenticationService;
import service.VotingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
            authService.getVoteMaterial(clientStub); // L'utilisateur est authentifié et reçoit un OTP
            votingService.vote(clientStub);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
