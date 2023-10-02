package service;

import model.Candidate;

import java.util.List;

//Définit les opérations que le serveur peut demander au client, comme obtenir le numéro d'étudiant ou afficher les candidats.
public interface ClientService {
    String getStudentNumber();
    String getPassword();
    void displayCandidates(List<Candidate> candidates);
}
