package bob.tasks;

import bob.exceptions.*;
import bob.storage.Storage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A list that stores all the tasks that the user creates, with methods that perform CRUD operations.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected int numberOfTasks = 0;

    public TaskList(ArrayList<Task> tasks, int numberOfTasks) {
        this.tasks = tasks;
        this.numberOfTasks = numberOfTasks;
    }

    /**
     * Adds task to the list and appends it to the data text file.
     *
     * @param task A task that the user creates
     */
    public void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
        Storage storage = new Storage();
        try {
            storage.appendToFile("bob.txt",task);
        }
        catch(IOException e) {
            System.out.println("error");
        }
    }

    /**
     * Removes a specified task in the list and in the data text file.
     *
     * @param index the index of the task in the TaskList
     */
    public void removeTask(int index) {
        tasks.remove(index);
        numberOfTasks--;
        Storage storage = new Storage();
        try {
            storage.writeToFile("bob.txt",tasks);
        }
        catch(IOException e) {
            System.out.println("error");
        }
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Prints out all the tasks in the task list.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". [" + tasks.get(i).getType() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
        }
    }

    /**
     * Marks a specific task as done, based on the user's 'mark' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     */
    public void markTask(String[] words) {
        try {
            if(words.length != 2) {
                throw new InvalidMarkCommand();
            }
            else if(Integer.parseInt(words[1]) > getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
                throw new ArrayIndexOutOfBoundsException();
            }
            else {
                System.out.println("Nice! I've marked this task as done:");
                int taskIndex = Integer.parseInt(words[1]) - 1;
                System.out.println("[" + tasks.get(taskIndex).getType() + "][X] " + tasks.get(taskIndex).getDescription());
                tasks.get(Integer.parseInt(words[1]) - 1).setIsDone(true);
                Storage storage = new Storage();
                storage.writeToFile("bob.txt",tasks);
            }
        }
        catch(InvalidMarkCommand e) {
            e.printError();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a valid task number.");
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a task number.");
        }
        catch (IOException e) {
            System.out.println("error");
        }
    }

    /**
     * Marks a specific task as not done, based on the user's 'unmark' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     */
    public void unmarkTask(String[] words) {
        try {
            if(words.length != 2) {
                throw new InvalidUnmarkCommand();
            }
            else if(Integer.parseInt(words[1]) > getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
                throw new ArrayIndexOutOfBoundsException();
            }
            else {
                System.out.println("OK, I've marked this task as not done yet:");
                int taskIndex = Integer.parseInt(words[1])-1;
                System.out.println("[" + tasks.get(taskIndex).getType() + "][ ] " + tasks.get(taskIndex).getDescription());
                tasks.get(Integer.parseInt(words[1])-1).setIsDone(false);
                Storage storage = new Storage();
                storage.writeToFile("bob.txt",tasks);
            }
        }
        catch(InvalidUnmarkCommand e) {
            e.printError();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a valid task number.");
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a task number.");
        }
        catch (IOException e) {
            System.out.println("error");
        }
    }

    /**
     * Adds a Todo task into the TaskList, based on the user's 'Todo' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     */
    public void addTodo(String[] words) {
        String description = "";
        int i = 1;
        while(i < words.length-1) {
            description += words[i] + " ";
            i++;
        }
        description += words[i];
        Todo task = new Todo(description);
        addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description);
        printNumberOfTasks();
    }

    /**
     * Adds a Deadline task into the TaskList, based on the user's 'Deadline' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     * @param line a string that contains the user's command input.
     */
    public void addDeadline(String[] words, String line) {
        try {
            if(!line.contains("/by") || words[1].equals("/by") || words[words.length-1].equals("/by")) {
                throw new InvalidDeadlineCommand();
            }
            String description = "";
            int i = 1;
            while(i < words.length-1) {
                description += words[i] + " ";
                i++;
            }
            description += words[i] + ")";
            Deadline task = new Deadline(description.replace("/by","(by:"));
            addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("[D][ ] " + task.getDescription());
            printNumberOfTasks();

        }
        catch(InvalidDeadlineCommand e) {
            e.printError();
        }
    }

    /**
     * Adds an Event task into the TaskList, based on the user's 'Event' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     * @param line a string that contains the user's command input.
     */
    public void addEvent(String[] words, String line) {
        try {
            if (!line.contains("/from") || !line.contains("/to")) {
                throw new InvalidEventCommand();
            }

            String firstWord = words[1];
            String lastWord = words[words.length - 1];

            if (firstWord.equals("/from") || firstWord.equals("/to") || lastWord.equals("/from") || lastWord.equals("/to")) {
                throw new InvalidEventCommand();
            }

            String description = "";
            int i = 1;
            while(i < words.length-1) {
                description += words[i] + " ";
                i++;
            }
            description += words[i] + ")";
            Event task = new Event(description.replace("/from","(from:").replace("/to","to:"));
            addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("[E][ ] " + task.getDescription());
            printNumberOfTasks();
        }
        catch(InvalidEventCommand e) {
            e.printError();
        }
    }

    /**
     * Deletes a specific task from the list, based on the user's 'delete' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     */
    public void deleteTask(String[] words) {
        try {
            if(words.length != 2) {
                throw new InvalidDeleteCommand();
            }
            else if(Integer.parseInt(words[1]) > getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
                throw new ArrayIndexOutOfBoundsException();
            }
            else {
                int taskIndex = Integer.parseInt(words[1])-1;
                System.out.println("Noted. I've removed this task:");
                System.out.println("[" + tasks.get(taskIndex).getType() + "][" + tasks.get(taskIndex).getStatusIcon() + "] " + tasks.get(taskIndex).getDescription());
                removeTask(taskIndex);
                printNumberOfTasks();
            }
        }
        catch (InvalidDeleteCommand e) {
            e.printError();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a valid task number.");
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a task number.");
        }
    }

    public void printNumberOfTasks() {
        if(getNumberOfTasks() == 1) {
            System.out.println("You now have 1 task in the list.");
        }
        else {
            System.out.println("You now have " + getNumberOfTasks() + " tasks in the list.");
        }
    }

    /**
     * Finds and prints all tasks in the list that match the keyword in the user's 'find' command.
     *
     * @param words a string array that contains all the words in the user's command input.
     */
    public void findTask(String[] words) {
        try {
            if (words.length != 2) {
                throw new InvalidFindCommand();
            }
            else {
                System.out.println("Here are the matching tasks in your list:");
                int i = 1;
                for(Task task : tasks) {
                    if(task.getDescription().contains(words[1])) {
                        System.out.println(i + ". [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
                        i++;
                    }
                }
            }
        }
        catch (InvalidFindCommand e) {
            e.printError();
        }
    }

}
