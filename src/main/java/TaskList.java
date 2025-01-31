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


}
