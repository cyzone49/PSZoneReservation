package service;

import command.commandData.GetAllServicePackagesCommandData;
import command.commandResult.ErrorResultData;
import command.commandResult.GetAllServicePackagesResultData;
import database.Database;
import model.ServicePackage;
import model.commandModels.CommandResult;

import java.util.List;

public class GetAllServicePackagesService {

    public CommandResult getAllPackages(GetAllServicePackagesCommandData commandData) {
        System.out.println("GOT Here");
        Database db = new Database();

        db.openConnection();
        List<ServicePackage> packages = db.getAllServicePackages();
        db.closeConnection(false);

        if(packages==null) {
            System.out.println("Failed! Database problem!");
            return new CommandResult(false, new ErrorResultData("Failed! Database problem!"));
        } else if(packages.size() == 0) {
            System.out.println("Failed! No packages found in database");
            return new CommandResult(false, new ErrorResultData("Failed! No packages found in database"));
        } else {
            System.out.println("\tSuccess!");
            return new CommandResult(true, new GetAllServicePackagesResultData(packages));
        }

    }
}
