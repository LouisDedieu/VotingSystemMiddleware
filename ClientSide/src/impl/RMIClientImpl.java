package impl;
import model.Candidate;
import model.User;
import rmi.RMIClient;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RMIClientImpl extends UnicastRemoteObject implements RMIClient {
    private String studentNumber;
    private String password;
    private User user;

    public RMIClientImpl() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your student number: ");
        studentNumber = scanner.nextLine();

        System.out.print("Enter your password: ");
        password = scanner.nextLine();
    }

    @Override
    public String getStudentNumber() throws RemoteException {
        return studentNumber;
    }

    @Override
    public String getPassword() throws RemoteException {
        return password;
    }

    @Override
    public User getUser() throws RemoteException {
        return user;
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void setUser(User user) throws RemoteException {
        this.user = user;
    }

    @Override
    public Map<Integer, Integer> getVotes(Candidate[] candidates) throws RemoteException{
        Map<Integer, Integer> votes = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        for (Candidate candidate : candidates) {
            System.out.print("Entrer une note pour entre 0 et 5 " + candidate.getName() + ": ");
            int Note = scanner.nextInt();
            votes.put(candidate.getRank(), Note);
        }
        return votes;
    }

    @Override
    public String getOTP() throws RemoteException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your OTP: ");
        String OTP = scanner.nextLine();
        return OTP;
    }
}
