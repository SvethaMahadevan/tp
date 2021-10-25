package seedu.duke.commands.module;

import seedu.duke.commands.Command;
import seedu.duke.module.ModuleManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class CapInfoCommand extends Command {

    ModuleManager moduleManager = new ModuleManager();

    public CapInfoCommand() {
        helpMessage = "Get user's information to calculate expected CAP";
        syntax = "module info";
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        System.out.println("What is your current cumulative average point (CAP)?");
        Scanner scanner = new Scanner(System.in);
        double currentCap = Double.parseDouble(ui.getUserInput(scanner));
        System.out.println("How many modular credits contributing to CAP you have taken?");
        int totalMcTaken = Integer.parseInt(ui.getUserInput(scanner));
        System.out.println("Thank you for your information. " +
                "You can view your expected CAP by keying in module cap");
        moduleManager.setCapInfo(currentCap, totalMcTaken);
    }
}
