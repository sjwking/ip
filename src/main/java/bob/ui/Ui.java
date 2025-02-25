package bob.ui;

import bob.parser.Command;
import bob.parser.Parser;
import bob.tasks.TaskList;

import java.util.Scanner;

/**
 * This class is responsible for displaying the main UI of the program.
 */
public class Ui {

    /**
     * Prints the Bob ASCII art into the terminal and greets the user.
     */
    public void printGreeting() {
        String logo = " _____             _ \n"
                + "|  _  \\           | |\n"
                + "| |_|  |   ____   | |\n"
                + "|     /   / __ \\  | |___\n"
                + "|  _  \\  | |  | | |  __ \\\n"
                + "| |_|  | | |__| | | |__| |\n"
                + "|_____/   \\____/  |_____/\n";

        System.out.println(logo);
        printDivider();
        System.out.println("Hello! I'm Bob!");
        System.out.println("What can I do for you?");
        printDivider();
    }


    /**
     * Prints a divider line between inputs and outputs in the terminal.
     */
    public void printDivider() {
        System.out.println("__________________________________________________________________________________________________________________");
    }

    /**
     * Reads the user's command input and throws it into the parser.
     *
     * @param list the task list.
     * @return a Command object based on the user's command input.
     */
    public Command readInput(TaskList list) {
        System.out.println("Enter input: ");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] words = line.split(" ");
        Parser parser = new Parser();
        Command command = new Command(line,words);
        parser.processInput(command,list);
        return command;
    }
}
