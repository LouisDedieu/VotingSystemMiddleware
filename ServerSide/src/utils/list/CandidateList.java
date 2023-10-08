package utils.list;

import model.candidate.Candidate;
import model.candidate.TextPitch;
import model.candidate.VideoPitch;

public class CandidateList {
    private static final Candidate[] candidates = {
        new Candidate(1, "Candidate1"),
        new Candidate(2, "Candidate2"),
        new Candidate(3, "Candidate3", new TextPitch("TextPitch")),
        new Candidate(4, "Candidate4", new VideoPitch("https://www.youtube.com/watch?v=dQw4w9WgXcQ")),
    };

    public static Candidate[] getCandidates() {
        return candidates;
    }
}
