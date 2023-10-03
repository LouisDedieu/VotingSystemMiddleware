import impl.AuthenticationServiceImpl;
import impl.VotingServiceImpl;
import model.Candidate;
import model.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

public class ServerMain {
    public static void main(String[] args) {
        Candidate candidate1 = new Candidate(1, "candidate1");
        Candidate candidate2 = new Candidate(2, "candidate2");

        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");
        User user3 = new User("user3", "password3");
        try {
            // Instanciez l'objet distant pour l'authentification
            AuthenticationServiceImpl authService = new AuthenticationServiceImpl(10001);

            // Instanciez l'objet distant pour le vote
            VotingServiceImpl votingService = new VotingServiceImpl(10002);

            // Créez le registre RMI sur le port spécifié
            LocateRegistry.createRegistry(2001);

            // Liez les objets distants au registre avec des noms uniques
            Naming.rebind("rmi://localhost:2001/authenticationService", authService);
            Naming.rebind("rmi://localhost:2001/votingService", votingService);

            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
