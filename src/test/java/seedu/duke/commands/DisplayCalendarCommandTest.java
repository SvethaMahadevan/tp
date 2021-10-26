package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.calendar.DisplayCommand;
import seedu.duke.exceptions.calendar.InvalidMonthException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayCalendarCommandTest {

    @Test
    void displayCalendar_wrongMonthValue_throwException() throws InvalidMonthException {
        try {
            Command command = new DisplayCommand("calendar 22-2021");
        } catch (Exception e) {
            assertEquals("The month has to be a value between 01-12!", e.getMessage());
        }
    }

}