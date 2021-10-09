package seedu.duke.commands;

import seedu.duke.exceptions.ClickException;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.storage.Storage;

import java.util.Scanner;

//@@author nvbinh15

/**
 * An abstract representation of Command.
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @param in Scanner to read input
     * @throws ClickException If there is an exception of type ClickException occurs.
     * @throws Exception If there is an exception of type other than DukeException occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui,
                                 Storage storage, Scanner in) throws ClickException, Exception;
}
