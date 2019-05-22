package service;

import command.commandData.AddSlotCommandData;
import model.commandModels.CommandResult;
import command.commandResult.AddSlotResultData;
import command.commandResult.ErrorResultData;
import database.Database;
import model.Slot;

public class AddSlotService {

    public CommandResult addSlot(AddSlotCommandData commandData) {
        Database db = new Database();

        db.openConnection();
        Slot slot = new Slot(commandData.getLocationID(), commandData.getPackageID());

        boolean success = db.insertSlot(slot);
        db.closeConnection(success);

        if (success) {
            System.out.println("\tSUCCESS. New slot inserted into DB");
            return new CommandResult(true, new AddSlotResultData());

        } else {
            System.out.println("\tFAILED. New slot NOT inserted into DB");
            return new CommandResult(false, new ErrorResultData("Add Slot failed!"));
        }
    }
}
