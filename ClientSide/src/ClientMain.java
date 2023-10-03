import impl.RMIClientImpl;
import service.AuthenticationService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);
            AuthenticationService authService = (AuthenticationService) registry.lookup("authenticationService");

            RMIClientImpl clientStub = new RMIClientImpl();
            authService.authenticate(clientStub);

            // Suite de votre logique client...

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
