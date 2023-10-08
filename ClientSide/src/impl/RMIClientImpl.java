package impl;
import model.candidate.Candidate;
import model.user.User;
import model.vote.Vote;
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
    private Scanner scanner;

    public RMIClientImpl() throws RemoteException {
        scanner = new Scanner(System.in);

//        System.out.print("Enter your student number: ");
//        studentNumber = scanner.nextLine();

//        System.out.print("Enter your password: ");
//        password = scanner.nextLine();
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
    public String askStudentNumber() throws RemoteException {
        System.out.print("Enter your student number: ");
        studentNumber = scanner.nextLine();
        return studentNumber;
    }

    @Override
    public String askPassword() throws RemoteException {
        System.out.print("Enter your password: ");
        password = scanner.nextLine();
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
    public Map<Integer, Vote> getVotes(Candidate[] candidates) throws RemoteException{
        Map<Integer, Vote> votes = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        for (Candidate candidate : candidates) {
            System.out.print("Entrer une note pour entre 0 et 5 " + candidate.getName() + ": ");

            int note = scanner.nextInt();
            while (note < 0 || note > 5) {
                System.out.print("Entrer une note pour entre 0 et 5 " + candidate.getName() + ": ");
                note = scanner.nextInt();
            }
            votes.put(candidate.getRank(), new Vote(candidate.getRank(), note));
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

    @Override
    public String askForVoterName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        return name;
    }
}
