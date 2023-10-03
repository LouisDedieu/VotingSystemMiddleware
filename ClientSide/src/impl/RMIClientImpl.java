package impl;
import rmi.RMIClient;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIClientImpl extends UnicastRemoteObject implements RMIClient {
    private String studentNumber;
    private String password;

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
    public void displayMessage(String message) {
        System.out.println(message);
    }

}
