package utils.list;

import model.candidate.Candidate;

public class CandidateList {
    private static final Candidate[] candidates = {
        new Candidate(1, "Candidate 1"),
        new Candidate(2, "Candidate 2"),
    };

    public static Candidate[] getCandidates() {
        return candidates;
    }
}
