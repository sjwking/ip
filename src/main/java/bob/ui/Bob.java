package bob.ui;

import java.util.Scanner;
import bob.tasks.TaskList;

public class Bob {
    public static void main(String[] args) {

        boolean isExit = false;

        TaskList list = new TaskList();
        list.processFile();
        printGreeting();

        while(!isExit) {
            System.out.println("Enter input: ");
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            String[] words = line.split(" ");

            printDivider();
            isExit = list.processInput(words,line);
            printDivider();
        }
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
        System.out.println("__________________________________________________________________________________________________________________");
    }


}
