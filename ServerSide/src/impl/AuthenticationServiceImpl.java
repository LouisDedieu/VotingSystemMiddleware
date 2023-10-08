package impl;

import exceptions.BadCredentialsException;
import model.user.OTP;
import rmi.RMIClient;
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

        String studentNumber = clientStub.askStudentNumber();
        String password = clientStub.askPassword();
        if (usersList.isValidUser(studentNumber, password)) {
            if (this.otpsList.getOTP(studentNumber) == null) {
                this.otpsList.addOTP(new OTP(studentNumber, generateOTP(studentNumber)));
            }
            clientStub.displayMessage("Votre One Time Password (OTP) est : " + this.otpsList.getOTP(studentNumber).getOtpValue());
            clientStub.setUser(usersList.getUser(studentNumber));
            clientStub.displayMessage("Vous etes connecte");
        } else {
            throw new BadCredentialsException("Mauvais identifiants");
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
