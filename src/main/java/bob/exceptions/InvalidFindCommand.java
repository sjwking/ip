package bob.exceptions;

public class InvalidFindCommand extends Exception {

    public void printError() {
        System.out.println("Please enter a valid find command. (Format: find <keyword>)");
    }
}
