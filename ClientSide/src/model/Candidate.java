package model;

public class Candidate implements java.io.Serializable{
    private int rank;
    private String name;
    private Pitch pitch;

    public Candidate(int rank, String name) {
        this(rank, name, null);
    }

    public Candidate(int rank, String name, Pitch pitch) {
        this.rank = rank;
        this.name = name;
        this.pitch = pitch;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public Pitch getPitch() {
        return pitch;
    }

    @Override
    public String toString() {
        return rank + " " + name + (pitch != null ? (" Pitch: " + pitch.present()) : "");
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }
}
