package utils;

public class RMIUtils {
    public static final String AUTHENTICATION_SERVICE_NAME= "AuthenticationService";
    public static final String VOTING_SERVICE_NAME = "VotingService";

    public static final int RMI_PORT = 2001;
    public static final int AUTHENTICATION_SERVICE_PORT = 10001;
    public static final int VOTING_SERVICE_PORT = 10002;
    public static final String RMI_HOST = "localhost";
    public static final String RMI_URL = "rmi://" + RMI_HOST + ":" + RMI_PORT + "/";
}
