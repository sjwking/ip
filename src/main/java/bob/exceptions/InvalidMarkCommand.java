package bob.exceptions;

public class InvalidMarkCommand extends Exception {

    public void printError() {
        System.out.println("Please enter a valid mark command. (Format: mark + <task number>)");
    }

}
