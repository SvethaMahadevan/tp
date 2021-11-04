package seedu.duke.commands.food;

import seedu.duke.commands.Command;
import seedu.duke.constants.Messages;
import seedu.duke.exceptions.syntax.ArgumentsNotFoundException;
import seedu.duke.exceptions.ClickException;
import seedu.duke.exceptions.food.InvalidStoreIndexException;
import seedu.duke.exceptions.syntax.WrongDividerOrderException;
import seedu.duke.exceptions.food.CannotFindFoodStoreException;
import seedu.duke.exceptions.food.FoodIndexNotFoundException;
import seedu.duke.exceptions.food.InvalidItemIndexException;
import seedu.duke.exceptions.food.NoItemDividerException;
import seedu.duke.exceptions.food.NoStoreDividerException;
import seedu.duke.food.FoodRecord;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageFood;
import seedu.duke.ui.Ui;

/**
 * Allows the user to add a food from a reference list.
 * radd refers to "reference add".
 */
public class AddFoodFromReferenceCommand extends Command {
    private int storeIndex = 1;
    private int itemIndex = 1;
    private String storeDivider = "s/";
    private String itemDivider = "i/";

    public AddFoodFromReferenceCommand() {
        this.syntax = "food radd s/ [STORE_INDEX] i/ [ITEM_INDEX]";
    }

    //assumes string given after food radd
    public AddFoodFromReferenceCommand(String userInput) throws
            WrongDividerOrderException,
            ArgumentsNotFoundException,
            NumberFormatException,
            InvalidStoreIndexException,
            InvalidItemIndexException,
            NoStoreDividerException,
            NoItemDividerException {
        parseDataAndThrowExceptionsIfFound(userInput);
    }

    /**
     * Parses user input into store and item indexes.
     * @param userInput user input.
     * @throws InvalidStoreIndexException if store index is invalid.
     * @throws InvalidItemIndexException if item index is invalid.
     * @throws NoStoreDividerException if store divider is missing.
     * @throws NoItemDividerException if item divider is missing.
     * @throws WrongDividerOrderException if divider order is wrong.
     * @throws ArgumentsNotFoundException if arguments not found.
     */
    private void parseDataAndThrowExceptionsIfFound(String userInput) throws
            InvalidStoreIndexException,
            InvalidItemIndexException,
            NoStoreDividerException,
            NoItemDividerException,
            WrongDividerOrderException,
            ArgumentsNotFoundException {
        throwExceptionsIfFound(userInput);
        String[] data = Parser.getData(userInput, storeDivider, itemDivider);
        String storeIndexString = data[0];
        String itemIndexString = data[1];
        storeIndex = Integer.parseInt(storeIndexString);
        itemIndex = Integer.parseInt(itemIndexString) - 1;
    }

    /**
     * Throws exceptions if found
     * @param userInput user input.
     * @throws InvalidStoreIndexException if store index is invalid.
     * @throws InvalidItemIndexException if item index is invalid.
     * @throws NoStoreDividerException if no store divider is found.
     * @throws NoItemDividerException if no item divider is found.
     */
    private void throwExceptionsIfFound(String userInput) throws
            InvalidStoreIndexException,
            InvalidItemIndexException,
            NoStoreDividerException,
            NoItemDividerException {
        if (storeIndex <= 0) {
            throw new InvalidStoreIndexException(storeIndex);
        }
        if (itemIndex <= 0) {
            throw new InvalidItemIndexException(itemIndex);
        }
        if (!userInput.contains(storeDivider)) {
            throw new NoStoreDividerException();
        }
        if (!userInput.contains(itemDivider)) {
            throw new NoItemDividerException();
        }
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ClickException, Exception {
        FoodRecord toAdd = getFoodRecordFromStall(storage);
        Ui.printLine();
        Ui.printMessageSameLine(Messages.PRINT_ADDING_ITEM);
        Ui.printMessage(toAdd.toString());
        storage.whatIAteTodayList.addToList(toAdd, false);
        StorageFood.saveList(storage.whatIAteTodayList);
    }

    /**
     * Gets a food record from a stall.
     * @param storage main storage class that contains the reference list.
     * @return toAdd food record extracted from the stall in reference list.
     * @throws FoodIndexNotFoundException if food index is not found.
     */
    private FoodRecord getFoodRecordFromStall(Storage storage) throws
            FoodIndexNotFoundException,
            CannotFindFoodStoreException {
        FoodRecord toAdd = storage
            .reference
            .getTechnoEdge()
            .getFoodRecordFromStall(storeIndex, itemIndex);
        return toAdd;
    }
}
