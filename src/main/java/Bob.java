import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {

        String logo = " _____             _ \n"
                + "|  _  \\           | |\n"
                + "| |_|  |   ____   | |\n"
                + "|     /   / __ \\  | |___\n"
                + "|  _  \\  | |  | | |  __ \\\n"
                + "| |_|  | | |__| | | |__| |\n"
                + "|_____/   \\____/  |_____/\n";
            

        System.out.println(logo);
        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Bob!");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");


        boolean isExit = false;
        TaskList list = new TaskList();

        while(!isExit) {
            System.out.println("Enter input: ");
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            String[] words = line.split(" ");

            System.out.println("_________________________________________");

            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isExit = true;
            }
            else if(line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < 100 && list.tasks[i] != null; i++) {
                    System.out.println(i+1 + ". [" + list.tasks[i].getStatusIcon() + "] " + list.tasks[i].getDescription());
                }
            }
            else if(words[0].equals("mark")) {
                if(words.length != 2) {
                    System.out.println("Please enter a valid mark command.");
                }
                else if(Integer.parseInt(words[1]) > list.getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
                    System.out.println("Please enter a valid task number.");
                }
                else {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + list.tasks[Integer.parseInt(words[1])-1].getDescription());
                    list.tasks[Integer.parseInt(words[1])-1].setIsDone(true);
                }
            }
            else if(words[0].equals("unmark")) {
                if(words.length != 2) {
                    System.out.println("Please enter a valid unmark command.");
                }
                else if(Integer.parseInt(words[1]) > list.getNumberOfTasks() || Integer.parseInt(words[1]) <= 0){
                    System.out.println("Please enter a valid task number.");
                }
                else {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + list.tasks[Integer.parseInt(words[1])-1].getDescription());
                    list.tasks[Integer.parseInt(words[1])-1].setIsDone(false);
                }
            }
            else {
                Task task = new Task(line);
                list.addTask(task);
                System.out.println("Added: " + line);
            }

            System.out.println("_________________________________________");
        }

    }

   
}
