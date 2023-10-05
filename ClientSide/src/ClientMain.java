import impl.RMIClientImpl;
import model.Candidate;
import service.AuthenticationService;
import service.VotingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);
            AuthenticationService authService = (AuthenticationService) registry.lookup("authenticationService");
            VotingService votingService = (VotingService) registry.lookup("votingService");

            // Récupérez et affichez la liste des candidats
            Candidate[] candidates = votingService.getCandidates();
            System.out.println("Liste des candidats :");
            for (Candidate candidate : candidates) {
                System.out.println(candidate.toString());
            }

            RMIClientImpl clientStub = new RMIClientImpl();
            authService.authenticate(clientStub);

            // Suite de votre logique client...

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
