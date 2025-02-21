package bob.exceptions;

public class InvalidDeleteCommand extends Exception {

    public void printError() {
      System.out.println("Please enter a valid delete command. (Format: delete + <task number>)");
    }
}
