package seedu.duke.commands;

import seedu.duke.exceptions.InvalidArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListTasksCommand extends Command {

    public ListTasksCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui,
                        Storage storage, Scanner in) throws IOException, InvalidArgumentsException {
        ArrayList<Task> tasks = storage.readTaskList();
        Ui.printTaskList(tasks);
    }

}