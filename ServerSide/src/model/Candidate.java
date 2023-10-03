package model;

public class Candidate implements java.io.Serializable{
    private int rank;
    private String name;
//    private Object pitch;

    public Candidate(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

//  public Object getPitch() {}

    @Override
    public String toString() {
        return rank + " " + name;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

//  public void setPitch(Object pitch) {}
}
