package service;

import command.commandData.AddServicePackageCommandData;
import model.commandModels.CommandResult;
import command.commandResult.AddServicePackageResultData;
import command.commandResult.ErrorResultData;
import database.Database;
import model.ServicePackage;

public class AddServicePackageService {

    public CommandResult addServicePackage(AddServicePackageCommandData commandData) {
        Database db = new Database();
        db.openConnection();

        ServicePackage servicePackage = new ServicePackage(
                commandData.getPackageName(),
                commandData.getHourlyRate(),
                commandData.getMaxOccupancy());

        boolean success = db.insertServicePackage(servicePackage);
        db.closeConnection(success);

        if (success) {
            System.out.println("\tSuccess! ServicePackage added");
            return new CommandResult(true, new AddServicePackageResultData());

        } else {
            System.out.println("\tFailed! ServicePackage NOT added");
            return new CommandResult(false, new ErrorResultData("Failed! package not added to database"));
        }
    }
}
