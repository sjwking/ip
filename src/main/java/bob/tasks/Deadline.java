package bob.tasks;

public class Deadline extends Task{
    public Deadline(String description){
        super(description);
        type = "D";
    }

    public void setStatus(String icon) {
        isDone = (icon.equals("1"));
    }
}
