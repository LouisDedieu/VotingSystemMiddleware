package model.vote;

import java.io.Serializable;

public class Vote implements Serializable {
    private int candidateRank;
    private int score;


    public Vote(int candidateRank, int score) {
        this.candidateRank = candidateRank;
        this.score = score;
    }

    public int getCandidateRank() {
        return candidateRank;
    }

    public int getScore() {
        return score;
    }

    public void setCandidateRank(int candidateRank) {
        this.candidateRank = candidateRank;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return candidateRank + " " + score;
    }
}

