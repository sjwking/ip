package bob.tasks;

public class Event extends Task{
    public Event(String description){
        super(description);
        type = "E";
    }

    public void setStatus(String icon) {
        isDone = (icon.equals("1"));
    }
}
