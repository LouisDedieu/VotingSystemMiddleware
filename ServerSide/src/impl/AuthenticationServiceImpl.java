package impl;

import exceptions.BadCredentialsException;
import exceptions.HasAlreadyVotedException;
import model.OTP;
import model.User;
import rmi.RMIClient;
import rmi.VotingStub;
import service.AuthenticationService;
import utils.OTPsList;
import utils.UsersList;

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
    public void authenticate(RMIClient clientStub) throws RemoteException, BadCredentialsException {

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

    @Override
    public VotingStub getVotingMaterial(String otp) throws RemoteException, HasAlreadyVotedException {
//        // Ici, validez l'OTP avant de renvoyer le matériel de vote
//        // Pour cet exemple, j'assume qu'un étudiant ne peut avoir qu'un seul OTP à la fois
//        // Vous devriez avoir un moyen de récupérer le studentNumber à ce stade, peut-être via le clientStub
//        String studentNumber = this.otpsList.getStudentNumber(otp);
//
//        if (otpsList.validateAndUseOTP(studentNumber, otp)) {
//            // TODO: renvoyez le stub de vote
//        } else {
//            throw new HasAlreadyVotedException("Invalid OTP or already used!");
//        }
        return null;
    }

    public OTPsList getOtpsList() {
        return otpsList;
    }
}
