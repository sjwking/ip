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


        boolean mainFlag = true;
        while(mainFlag) {
            System.out.println("Enter input: ");
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();

            System.out.println("_________________________________________");

            if(line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                mainFlag = false;
            }
            else {
                System.out.println(line);
            }

            System.out.println("_________________________________________");
        }

    }

   
}
