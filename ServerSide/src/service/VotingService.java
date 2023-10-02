package service;

import java.rmi.Remote;
import java.util.Map;

public interface VotingService extends Remote {
    public void vote(String studentNumber, Map<Integer, Integer> votes, String OTP);
}
