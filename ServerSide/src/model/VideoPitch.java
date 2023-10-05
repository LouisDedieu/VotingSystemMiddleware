package model;

public class VideoPitch implements Pitch {
    private String videoUrl;

    public VideoPitch(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String present() {
        return "Watch the pitch at: " + videoUrl;
    }
}
