package bob.tasks;

import bob.exceptions.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected int numberOfTasks = 0;

    public void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
        try {
            appendToFile("bob.txt",task);
        }
        catch(IOException e) {
            System.out.println("error");
        }
    }

    public void removeTask(int index) {
        tasks.remove(index);
        numberOfTasks--;
        try {
            writeToFile("bob.txt");
        }
        catch(IOException e) {
            System.out.println("error");
        }
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public boolean processInput(String[] words, String line) {
        switch(words[0]) {
        case "bye":
            return exitProgram();
        case "list":
            this.printList();
            break;
        case "mark":
            this.markTask(words);
            break;
        case "unmark":
            this.unmarkTask(words);
            break;
        case "todo":
            this.addTodo(words);
            break;
        case "deadline":
            this.addDeadline(words,line);
            break;
        case "event":
            this.addEvent(words,line);
            break;
        case "delete":
            this.deleteTask(words);
            break;
        default:
            System.out.println("Please enter a valid command.");
            break;
        }
        return false;
    }

    public boolean exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        return true;
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". [" + tasks.get(i).getType() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
        }

    }

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
                writeToFile("bob.txt");
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
                writeToFile("bob.txt");
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

    public void processFile() {
        try {
            File file = new File("bob.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    retrieveData(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
    }


    public void retrieveData(String line) {
        String[] words = line.split("\\|");
        Task task;
        switch (words[0]) {
        case "T" -> task = new Todo(words[2]);
        case "D" -> task = new Deadline(words[2]);
        case "E" -> task = new Event(words[2]);
        default -> task = new Task(words[2]);
        }
        task.setIsDone(words[1].equals("X"));
        tasks.add(task);
        numberOfTasks++;
    }


    public void appendToFile(String filePath, Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath,true);
        String line = task.getType() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n";
        fileWriter.write(line);
        fileWriter.close();
    }

    public void writeToFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for(Task task : tasks) {
            fileWriter.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.getDescription() + "\n");
        }
        fileWriter.close();
    }


}
