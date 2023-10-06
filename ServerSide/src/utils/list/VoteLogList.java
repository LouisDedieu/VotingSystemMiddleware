package utils.list;

import model.vote.VoteLog;

import java.util.ArrayList;
import java.util.List;

public class VoteLogList {
    private static final List<VoteLog> voteLogs = new ArrayList<>();

    public static List<VoteLog> getVoteLogs() {
        return voteLogs;
    }

    public static VoteLog getVoteLog(String studentNumber) {
        for (VoteLog voteLog : voteLogs) {
            if (voteLog.getStudentNumber().equals(studentNumber)) {
                return voteLog;
            }
        }
        return null;
    }

    // Ajout d'une méthode pour ajouter un VoteLog à la liste
    public static void addVoteLog(VoteLog voteLog) {
        voteLogs.add(voteLog);
    }
}
