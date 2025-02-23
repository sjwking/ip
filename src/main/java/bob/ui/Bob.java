package bob.ui;

import bob.parser.Command;
import bob.tasks.TaskList;
import bob.storage.Storage;

public class Bob {

    public void run() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        boolean isExit = false;

        ui.printGreeting();
        TaskList list = storage.retrieveList();

        while(!isExit) {
            Command command = ui.readInput(list);
            isExit = command.isExit();
            ui.printDivider();
        }
    }

    public static void main(String[] args) {
        new Bob().run();
    }

}
