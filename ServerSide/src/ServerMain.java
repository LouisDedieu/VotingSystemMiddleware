import impl.AuthenticationServiceImpl;
import impl.VotingServiceImpl;
import model.Candidate;
import model.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Instanciez l'objet distant pour l'authentification
            AuthenticationServiceImpl authService = new AuthenticationServiceImpl(10001);

            // Instanciez l'objet distant pour le vote
            VotingServiceImpl votingService = new VotingServiceImpl(10002, authService);

            // Créez le registre RMI sur le port spécifié
            LocateRegistry.createRegistry(2001);

            // Liez les objets distants au registre avec des noms uniques
            Naming.rebind("rmi://localhost:2001/authenticationService", authService);
            Naming.rebind("rmi://localhost:2001/votingService", votingService);



            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Entrez 'start' pour démarrer le vote, 'stop' pour arrêter, ou 'exit' pour quitter.");
                String input = scanner.nextLine();
                if ("start".equalsIgnoreCase(input)) {
                    // Démarrer le vote
                    votingService.startVoting();
                    System.out.println("Vote démarré.");
                } else if ("stop".equalsIgnoreCase(input)) {
                    // Arrêter le vote
                    votingService.stopVoting();
                    System.out.println("Vote arrêté.");
                } else if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Fermeture du serveur.");
                    System.exit(0);
                } else {
                    System.out.println("Commande non reconnue.");
                }
            }

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
