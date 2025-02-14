public class InvalidDeadlineCommand extends Exception{

    public void printError() {
        System.out.println("Please enter a valid deadline command. (Format: deadline <description> /by <deadline>)");
    }
}
