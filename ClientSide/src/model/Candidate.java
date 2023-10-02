package model;

//Répliquée côté client pour garantir que client et serveur ont une représentation cohérente des données.
public class Candidate {
    private int rank;
    private String name;
    private Object pitch;

    public Candidate(int rank, String name, Object pitch) {
        this.rank = rank;
        this.name = name;
        this.pitch = pitch;
    }

    //Getters
    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public Object getPitch() {
        return pitch;
    }
}
