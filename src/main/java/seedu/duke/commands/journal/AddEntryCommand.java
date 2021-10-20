package seedu.duke.commands.journal;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.EmptyEntryArgumentsException;
import seedu.duke.exceptions.EmptyEntryNameException;
import seedu.duke.exceptions.journal.EmptyNoteNameException;
import seedu.duke.exceptions.journal.NotebookArgumentNotFoundException;
import seedu.duke.exceptions.journal.NotebookNotFoundForEntryAddition;
import seedu.duke.journal.Note;
import seedu.duke.parser.journal.ParserJournal;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageEntries;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class AddEntryCommand extends Command {
    public String userInput;

    public AddEntryCommand() {
        syntax = "journal entry n/ NOTEBOOK_NAME e/ ENTRY_NAME";
    }

    /**
     * Constructor for the AddEntryCommand.
     *
     * @param userInput input from the user
     */
    public AddEntryCommand(String userInput) {
        this.userInput = userInput;
        this.helpMessage = "Add an entry to the desired notebook";
        this.syntax = "journal entry n/ NOTEBOOK_NAME e/ ENTRY_NAME";
    }

    /**
     * Allows for the adding of entry.
     *
     * @param ui allows for printing of a message to indicate that the entry has been added
     * @param storage to allow for storing of entries
     * @throws EmptyNoteNameException No note name entered after 'n/'
     * @throws EmptyEntryArgumentsException if no arguments found for the entry
     */
    @Override
    public void execute(Ui ui, Storage storage) throws EmptyNoteNameException, EmptyEntryArgumentsException,
            NotebookArgumentNotFoundException, EmptyEntryNameException, IOException,
            NotebookNotFoundForEntryAddition {
        String[] argumentsNoteEntry = ParserJournal.parseAddEntryCommand(userInput, storage);
        ArrayList<Note> notes = storage.collectionOfNotes.getNotesArrayList();
        for (Note note : notes) {
            if (note.getNoteName().equals(argumentsNoteEntry[0])) {
                storage.collectionOfEntries.addEntry(argumentsNoteEntry[0], argumentsNoteEntry[1]);
            }
        }
        ui.printAddedEntryMessage(argumentsNoteEntry[1]);
        StorageEntries.writeEntries(storage.collectionOfEntries);
    }
}