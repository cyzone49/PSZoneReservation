package service;

import command.commandData.GetAvailableSlotsCommandData;
import command.commandResult.ErrorResultData;
import model.commandModels.CommandResult;
import command.commandResult.GetAvailableSlotsResultData;
import database.Database;
import model.Slot;

import java.util.List;

public class GetAvailableSlotsService {

    public CommandResult getAvailableSlots(GetAvailableSlotsCommandData commandData) {
        int locationID = commandData.getLocationID();
        int packageID = commandData.getPackageID();

        Database db = new Database();

        db.openConnection();
        List<Slot> slots = null;

        if( locationID == -1 && packageID == -1 ) {
            slots = db.getAllAvailableSlots();
        }
        else if ( locationID != -1 && packageID == -1 ) {
            slots = db.getAvailableSlotsByLocation(locationID);
        }
        else if ( locationID == -1 && packageID != -1 ) {
            slots = db.getAvailableSlotsByPackage(packageID);
        }
        else { // locationID != -1 && packageID != -1
            slots = db.getAvailableSlotsByLocationAndPackage(locationID, packageID);
        }
        db.closeConnection(false);

        if(slots == null) {
            System.out.println("\tDatabase error! Failed to fetch slots data");
            return new CommandResult(false, new ErrorResultData("Database error! Failed to fetch slots data"));
        } else if (slots.size() == 0) {
            System.out.println("\tThere is no slot available");
            return new CommandResult(false, new ErrorResultData("There is no slot available"));
        } else {
            System.out.println("\tSuccess!");
            return new CommandResult(true, new GetAvailableSlotsResultData(slots));
        }

    }
}
