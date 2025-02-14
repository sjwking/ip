public class TaskList {
    protected Task[] tasks = new Task[100];
    protected int numberOfTasks = 0;

    public void addTask(Task task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
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
            default:
                this.addTask(line);
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
        for(int i = 0; tasks[i] != null; i++) {
            System.out.println(i+1 + ". [" + tasks[i].getType() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
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
                System.out.println("[" + tasks[taskIndex].getType() + "][X] " + tasks[taskIndex].getDescription());
                tasks[Integer.parseInt(words[1]) - 1].setIsDone(true);
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
                System.out.println("[" + tasks[taskIndex].getType() + "][ ] " + tasks[taskIndex].getDescription());
                tasks[Integer.parseInt(words[1])-1].setIsDone(false);
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
        System.out.println("Now you have " + getNumberOfTasks() + " tasks in the list.");
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
            System.out.println("Now you have " + getNumberOfTasks() + " tasks in the list.");

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
            System.out.println("Now you have " + getNumberOfTasks() + " tasks in the list.");
        }
        catch(InvalidEventCommand e) {
            e.printError();
        }
    }

    public void addTask(String line) {
        Task task = new Task(line);
        addTask(task);
        System.out.println("Added: " + line);
        if(getNumberOfTasks() == 1) {
            System.out.println("You now have 1 task in the list.");
        }
        else {
            System.out.println("You now have " + getNumberOfTasks() + " tasks in the list.");
        }
    }


}
