package impl;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import model.user.OTP;
import rmi.RMIClient;
import rmi.VotingStub;
import service.AuthenticationService;
import utils.list.OTPsList;
import utils.list.UsersList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {
    private UsersList usersList;
    private OTPsList otpsList;
    public AuthenticationServiceImpl(int port) throws RemoteException {
        super(port);
        this.usersList = new UsersList();
        this.otpsList = new OTPsList();
    }

    @Override
    public void getVoteMaterial(RMIClient clientStub) throws RemoteException, BadCredentialsException {

        String studentNumber = clientStub.getStudentNumber();
        String password = clientStub.getPassword();
        if (usersList.isValidUser(studentNumber, password)) {
            this.otpsList.addOTP(new OTP(studentNumber, generateRandomOTP()));
            clientStub.displayMessage("Your One Time Password (OTP) is : " + this.otpsList.getOTP(studentNumber).getOtpValue());
            clientStub.displayMessage("OTP already used ? " + this.otpsList.getOTP(studentNumber).isUsed());
            clientStub.setUser(usersList.getUser(studentNumber));
            clientStub.displayMessage("You are successfully authenticated!");
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    @Override
    public String generateOTP(String studentNumber) {
        String otpValue = generateRandomOTP();
        OTP otp = new OTP(studentNumber, otpValue);
        otpsList.addOTP(otp);
        return otpValue; // Envoyez cet OTP au client (en pratique, vous voudriez peut-être envoyer par e-mail ou autre méthode sécurisée)
    }

    private String generateRandomOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));  // Génère un OTP à 6 chiffres
    }

    public OTPsList getOtpsList() {
        return otpsList;
    }
}
