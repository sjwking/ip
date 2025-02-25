package bob.parser;

import bob.tasks.TaskList;
import bob.ui.Ui;

/**
 * This class is responsible for making sense of the user's command input.
 */
public class Parser {

    /**
     * Processes the user's command input by looking at the first word.
     *
     * @param command a Command object based on the user's command input.
     * @param list the task list.
     */
    public void processInput(Command command, TaskList list) {
        new Ui().printDivider();

        switch(command.keyword) {
        case "bye":
            exitProgram(command);
            break;
        case "list":
            list.printList();
            break;
        case "mark":
            list.markTask(command.words);
            break;
        case "unmark":
            list.unmarkTask(command.words);
            break;
        case "todo":
            list.addTodo(command.words);
            break;
        case "deadline":
            list.addDeadline(command.words,command.line);
            break;
        case "event":
            list.addEvent(command.words,command.line);
            break;
        case "delete":
            list.deleteTask(command.words);
            break;
        case "find":
            list.findTask(command.words);
            break;
        default:
            System.out.println("Please enter a valid command.");
            break;
        }
    }

    /**
     * Exits the program when the user inputs the 'bye' command.
     *
     * @param command a Command object based on the user's command input.
     */
    public void exitProgram(Command command) {
        System.out.println("Bye. Hope to see you again soon!");
        command.isExit = true;
    }

}

