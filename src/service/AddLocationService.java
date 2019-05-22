package service;

import command.commandData.AddLocationCommandData;
import model.commandModels.CommandResult;
import command.commandResult.AddLocationResultData;
import command.commandResult.ErrorResultData;
import database.Database;
import model.Location;

public class AddLocationService {

    public CommandResult addLocation(AddLocationCommandData commandData) {
        System.out.println("AddLocation Service running...");
        Database db = new Database();

        db.openConnection();
        Location location = new Location(commandData.getLocationName(),
                                        commandData.getStreetAddress(),
                                        commandData.getCity(),
                                        commandData.getState(),
                                        commandData.getZipcode());

        boolean success = db.insertLocation(location);
        db.closeConnection(success);

        if (success) {
            System.out.println("\tSUCCESS. New Location inserted into DB");
            return new CommandResult(true, new AddLocationResultData());

        } else {
            System.out.println("\tFAILED. Location NOT inserted into DB");
            return new CommandResult(false, new ErrorResultData("Add Location failed!"));
        }

    }
}
