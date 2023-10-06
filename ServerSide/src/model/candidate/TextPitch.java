package model.candidate;

public class TextPitch implements Pitch {
    private String content;

    public TextPitch(String content) {
        this.content = content;
    }

    @Override
    public String present() {
        return content;
    }
}
