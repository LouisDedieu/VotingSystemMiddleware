import impl.AuthenticationServiceImpl;
import impl.VotingServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Instanciez l'objet distant pour l'authentification
            AuthenticationServiceImpl authService = new AuthenticationServiceImpl();

            // Instanciez l'objet distant pour le vote
            VotingServiceImpl votingService = new VotingServiceImpl();

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
