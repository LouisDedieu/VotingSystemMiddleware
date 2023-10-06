package utils.list;

import model.vote.VoteLog;

public class VoteLogList {
private static final VoteLog[] voteLogs = {

    };

    public VoteLogList() {
        // empty constructor
    }

    public static VoteLog[] getVoteLogs() {
        return voteLogs;
    }

    public VoteLog getVoteLog(String studentNumber) {
        for (VoteLog voteLog : voteLogs) {
            if (voteLog.getStudentNumber().equals(studentNumber)) {
                return voteLog;
            }
        }
        return null;
    }
}
