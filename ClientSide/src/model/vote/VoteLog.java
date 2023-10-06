package model.vote;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class VoteLog implements Serializable {
    private String studentNumber;
    private String voterName;
    private Date voteDate;
    private Map<Integer, Vote> votes;

    public VoteLog(String studentNumber, String voterName, Date voteDate, Map<Integer, Vote> votes) {
        this.studentNumber = studentNumber;
        this.voterName = voterName;
        this.voteDate = voteDate;
        this.votes = votes;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getVoterName() {
        return voterName;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public Map<Integer, Vote> getVotes() {
        return votes;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public void setVotes(Map<Integer, Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return studentNumber + " " + voterName + " " + voteDate + " " + votes;
    }
}

