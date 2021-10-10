package seedu.duke.commands;

import seedu.duke.food.FoodRecord;
import seedu.duke.exceptions.IllegalFoodParameterException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageFood;
import seedu.duke.ui.Ui;

import java.io.IOException;

/**
 * Command to add a food item to the existing  list.
 * prefix would be food add [FOOD_NAME] [CALORIE_COUNT(kcal)]
 *
 * @author ngnigel99
 */
public class AddFoodCommand extends Command {
    private String nameString;
    private String calorieString;

    public AddFoodCommand(String nameString, String calorieString) {
        super();
        this.nameString = nameString;
        this.calorieString = calorieString;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws IllegalFoodParameterException, IOException {
        FoodRecord foodRecord  = Parser.parseFoodRecord(nameString + " " + calorieString);
        storage.whatIAteTodayList.addToList(foodRecord);
        StorageFood.saveList(storage.whatIAteTodayList);
    }
}
