package impl;

//Implémentation concrète de ClientService.
import model.Candidate;

import java.util.List;

//Implémentation de ClientService
public class ClientServiceImpl {
    //Implémentation de getStudentNumber
    public String getStudentNumber() {
        return "123456789";
    }

    //Implémentation de getPassword
    public String getPassword() {
        return "password";
    }

    //Implémentation de displayCandidates
    public void displayCandidates(List<Candidate> candidates) {
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getRank() + " " + candidate.getName() + " " + candidate.getPitch());
        }
    }
}
