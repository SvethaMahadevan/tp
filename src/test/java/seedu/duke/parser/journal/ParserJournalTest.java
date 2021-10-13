package seedu.duke.parser.journal;

import org.junit.jupiter.api.Test;
import seedu.duke.journal.Note;
import seedu.duke.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserJournalTest {

    static ParserJournal parser = new  ParserJournal();
    static Storage storage;

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parserNotebookCommand_noNoteNameGiven() {
        try {
            Boolean isValidNoteName = ParserJournal.isValidNotebookCommand("journal notebook n/");
        } catch (Exception e) {
            assertEquals(false, false);
        }
    }

    @Test
    void parserNotebookCommand_noNoteArgumentsFound() {
        Boolean isValidNoteArguments = false;
        try {
            isValidNoteArguments = ParserJournal.isValidNotebookCommand("journal notebook");
        } catch (Exception e) {
            assertEquals(false, isValidNoteArguments);
        }
    }

    @Test
    void parserNotebookCommand_duplicateNotebookName_throwDuplicateNoteException() {
        ArrayList<Note> notes = storage.collectionOfNotes.getNotesArrayList();
        String noteName = " ";
        try {
            noteName = parser.checkDuplicateOrNot("journal notebook n/ notInStorageName ", notes);
        } catch (Exception e) {
            assertEquals("notInStorageName", noteName);
        }
    }
}