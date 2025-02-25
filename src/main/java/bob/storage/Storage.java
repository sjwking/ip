package bob.storage;

import bob.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles operations such as storing data in the Bob.txt file, and retrieving data from it.
 */
public class Storage {

    /**
     * Retrieves the task list from the text file and parses it into a TaskList object.
     * This is done everytime the user starts the program.
     * <p>
     *     A new text file is created upon running the program for the first time.
     * </p>
     * @return the retrieved task list
     */
    public TaskList retrieveList() {
        ArrayList<Task> tasks = new ArrayList<>();
        int numberOfTasks = 0;
        try {
            File file = new File("bob.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    Task task = retrieveTasks(scanner.nextLine());
                    tasks.add(task);
                    numberOfTasks++;
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        return new TaskList(tasks,numberOfTasks);
    }


    /**
     * Retrieves the tasks stored in the text file and parses them into Task objects.
     * @param line a string that contains the data of a row in the text file.
     * @return the retrieved Task object
     */
    public Task retrieveTasks(String line) {
        String[] words = line.split("\\|");
        Task task;
        switch (words[0]) {
        case "T" -> task = new Todo(words[2]);
        case "D" -> task = new Deadline(words[2]);
        case "E" -> task = new Event(words[2]);
        default -> task = new Task(words[2]);
        }
        task.setIsDone(words[1].equals("X"));
        return task;
    }


    /**
     * Appends the task information into the text file.
     * <p>
     *     Called when the user wants to add a task into the list.
     * </p>
     *
     * @param filePath the file path where Bob.txt is located in.
     * @param task the task that will be appended.
     * @throws IOException if Bob.txt cannot be found.
     */
    public void appendToFile(String filePath, Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath,true);
        String line = task.getType() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n";
        fileWriter.write(line);
        fileWriter.close();
    }

    /**
     * Writes into the text file the updated task list in the program.
     * <p>
     *     Called when the user wants to delete a task in the list.
     * </p>
     *
     * @param filePath the file path where Bob.txt is located in.
     * @param tasks the task list.
     * @throws IOException if Bob.txt cannot be found.
     */
    public void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for(Task task : tasks) {
            fileWriter.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n");
        }
        fileWriter.close();
    }

}
