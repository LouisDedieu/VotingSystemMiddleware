package model;

public class Candidate implements java.io.Serializable{
    private int rank;
    private String name;
    private Object pitch;

    public Candidate(int rank, String name, Object pitch) {
        this.rank = rank;
        this.name = name;
        this.pitch = pitch;
    }
}
