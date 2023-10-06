package utils.list;

import model.candidate.Candidate;

public class CandidateList {
    private static final Candidate[] candidates = {
        new Candidate(1, "Candidate1"),
        new Candidate(2, "Candidate2"),
    };

    public static Candidate[] getCandidates() {
        return candidates;
    }
}
