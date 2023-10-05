package impl;
import model.User;
import rmi.RMIClient;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIClientImpl extends UnicastRemoteObject implements RMIClient {
    private String studentNumber;
    private String password;
    private User user;

    public RMIClientImpl() throws RemoteException {
        this.getListCandidate();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your student number: ");
        studentNumber = scanner.nextLine();

        System.out.print("Enter your password: ");
        password = scanner.nextLine();
    }

    private void getListCandidate() {

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
}
