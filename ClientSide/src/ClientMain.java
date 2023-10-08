import exceptions.BadCredentialsException;
import impl.RMIClientImpl;
import model.candidate.Candidate;
import service.AuthenticationService;
import service.VotingService;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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




            do {
                RMIClientImpl clientStub = new RMIClientImpl();
                authService.getVoteMaterial(clientStub);
                votingService.vote(clientStub);
                System.out.println("Entrez 'vote' pour vous authentifier et voter, ou 'exit' pour quitter.");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Merci d'avoir utilisé le système de vote. Au revoir !");
                    break;
                } else if ("vote".equalsIgnoreCase(input)) {
                    authService.getVoteMaterial(clientStub);
                    votingService.vote(clientStub);
                } else {
                    System.out.println("Commande non reconnue.");
                }
            } while (true);
            } catch (BadCredentialsException e) {
                System.out.println(e.getMessage());
            } catch (AccessException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {

        }

    catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

