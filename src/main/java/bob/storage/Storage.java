package bob.storage;

import bob.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

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


    public void appendToFile(String filePath, Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath,true);
        String line = task.getType() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n";
        fileWriter.write(line);
        fileWriter.close();
    }

    public void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for(Task task : tasks) {
            fileWriter.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n");
        }
        fileWriter.close();
    }

}
