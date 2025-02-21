package bob.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        type = "T";
    }

    public void setStatus(String icon) {
        isDone = (icon.equals("1"));
    }
}
