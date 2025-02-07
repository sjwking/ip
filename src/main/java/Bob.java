import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        boolean isExit = false;
        TaskList list = new TaskList();

        printGreeting();

        while(!isExit) {
            System.out.println("Enter input: ");
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            String[] words = line.split(" ");

            printDivider();
            isExit = processInput(words,line,list);
            printDivider();
        }
    }

    public static boolean processInput(String[] words, String line, TaskList list) {
        switch(words[0]) {
            case "bye":
                return exitProgram();
            case "list":
                printList(list);
                break;
            case "mark":
                markTask(list,words);
                break;
            case "unmark":
                unmarkTask(list,words);
                break;
            case "todo":
                addTodo(list,words);
                break;
            case "deadline":
                addDeadline(list,words);
                break;
            case "event":
                addEvent(list,words);
                break;
            default:
                addTask(list,line);
                break;
        }
        return false;
    }

    public static boolean exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        return true;
    }

    public static void printList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; list.tasks[i] != null; i++) {
            System.out.println(i+1 + ". [" + list.tasks[i].getType() + "][" + list.tasks[i].getStatusIcon() + "] " + list.tasks[i].getDescription());
        }
    }

    public static void markTask(TaskList list, String[] words) {
        if(words.length != 2) {
            System.out.println("Please enter a valid mark command.");
        }
        else if(Integer.parseInt(words[1]) > list.getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
            System.out.println("Please enter a valid task number.");
        }
        else {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + list.tasks[Integer.parseInt(words[1])-1].getType() + "][X] " + list.tasks[Integer.parseInt(words[1])-1].getDescription());
            list.tasks[Integer.parseInt(words[1])-1].setIsDone(true);
        }
    }

    public static void unmarkTask(TaskList list, String[] words) {
        if(words.length != 2) {
            System.out.println("Please enter a valid unmark command.");
        }
        else if(Integer.parseInt(words[1]) > list.getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
            System.out.println("Please enter a valid task number.");
        }
        else {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + list.tasks[Integer.parseInt(words[1])-1].getType() + "][ ] " + list.tasks[Integer.parseInt(words[1])-1].getDescription());
            list.tasks[Integer.parseInt(words[1])-1].setIsDone(false);
        }
    }

    public static void addTodo(TaskList list, String[] words) {
        String description = "";
        int i = 1;
        while(i < words.length-1) {
            description += words[i] + " ";
            i++;
        }
        description += words[i];
        Todo task = new Todo(description);
        list.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + description);
        System.out.println("Now you have " + list.getNumberOfTasks() + " tasks in the list.");
    }

    public static void addDeadline(TaskList list, String[] words) {
        String description = "";
        int i = 1;
        while(i < words.length-1) {
            description += words[i] + " ";
            i++;
        }
        description += words[i] + ")";
        Deadline task = new Deadline(description.replace("/by","(by:"));
        list.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + task.getDescription());
        System.out.println("Now you have " + list.getNumberOfTasks() + " tasks in the list.");
    }

    public static void addEvent(TaskList list, String[] words) {
        String description = "";
        int i = 1;
        while(i < words.length-1) {
            description += words[i] + " ";
            i++;
        }
        description += words[i] + ")";
        Event task = new Event(description.replace("/from","(from:").replace("/to","to:"));
        list.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + task.getDescription());
        System.out.println("Now you have " + list.getNumberOfTasks() + " tasks in the list.");
    }

    public static void addTask(TaskList list, String line) {
        Task task = new Task(line);
        list.addTask(task);
        System.out.println("Added: " + line);
    }

    public static void printGreeting() {
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

    public static void printDivider() {
        System.out.println("_________________________________________");
    }




   
}
