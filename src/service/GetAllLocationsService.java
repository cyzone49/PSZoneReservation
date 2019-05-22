package service;

import command.commandData.GetAllLocationsCommandData;
import model.commandModels.CommandResult;
import command.commandResult.GetAllLocationsResultData;
import database.Database;
import model.Location;

import java.util.List;

public class GetAllLocationsService {

    public CommandResult getAllLocations(GetAllLocationsCommandData commandData) {
        Database db = new Database();

        db.openConnection();
        List<Location> locations = db.getAllLocations();
        db.closeConnection(false);

        if(locations==null) {
            System.out.println("Failed! Database problem!");
        } else if(locations.size() == 0) {
            System.out.println("Failed! No locations found in database");
        } else {
            System.out.println("\tSuccess!");
        }
        return new CommandResult(true, new GetAllLocationsResultData(locations));
    }
}
