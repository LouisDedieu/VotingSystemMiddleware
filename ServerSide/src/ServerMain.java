import impl.AuthenticationServiceImpl;
import impl.VotingServiceImpl;
import model.Candidate;
import model.User;
import utils.Command;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.util.Scanner;

import static utils.RMIUtils.*;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Instanciez l'objet distant pour l'authentification
            AuthenticationServiceImpl authService = new AuthenticationServiceImpl(AUTHENTICATION_SERVICE_PORT);

            // Instanciez l'objet distant pour le vote
            VotingServiceImpl votingService = new VotingServiceImpl(VOTING_SERVICE_PORT, authService);

            // Créez le registre RMI sur le port spécifié
            LocateRegistry.createRegistry(RMI_PORT);

            // Liez les objets distants au registre avec des noms uniques
            Naming.rebind(RMI_URL + AUTHENTICATION_SERVICE_NAME, authService);
            Naming.rebind(RMI_URL + VOTING_SERVICE_NAME, votingService);



            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Entrez 'start' pour démarrer le vote, 'stop' pour arrêter, ou 'exit' pour quitter.");
                String input = scanner.nextLine();
                Command command = Command.fromString(input);

                switch (command) {
                    case START -> votingService.handleStart();
                    case STOP -> votingService.handleStop();
                    case EXIT -> votingService.handleExit();
                    case UNKNOWN -> votingService.handleUnknownCommand();
                }
            }

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
