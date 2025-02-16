package bob.exceptions;

public class InvalidUnmarkCommand extends Exception{
    public void printError() {
        System.out.println("Please enter a valid unmark command. (Format: unmark + <task number>)");
    }
}
