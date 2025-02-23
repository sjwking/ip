package bob.parser;

public class Command {
    protected String line;
    protected String[] words;
    protected String keyword;
    protected boolean isExit = false;

    public Command(String line, String[] words) {
        this.line = line;
        this.words = words;
        this.keyword = words[0];
    }

    public boolean isExit() {
        return isExit;
    }
}
