public class InvalidEventCommand extends Exception{

    public void printError() {
        System.out.println("Please enter a valid event command. (Format: event <description> /from <start time> /to <end time>)");
    }
}
