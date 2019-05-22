package service;

import command.commandData.GetReservationByUserCommandData;
import command.commandResult.ErrorResultData;
import command.commandResult.GetReservationByUserResultData;
import database.Database;
import model.Reservation;
import model.commandModels.CommandResult;

import java.util.List;

public class GetReservationByUserService {

    public CommandResult getReservationByUser(GetReservationByUserCommandData commandData) {
        Database db = new Database();

        db.openConnection();
        List<Reservation> list = db.getReservationByUser(commandData.getUserID());

        db.closeConnection(false);

        if(list== null) {
            System.out.println("\tFailed! Database failure");
            return new CommandResult(false, new ErrorResultData("Database Failure"));
        }
        else if(list.size()==0) {
            System.out.println("\tFailed to find reservation by user ID");
            return new CommandResult(false, new ErrorResultData("Failed to find reservation by user ID"));
        }
        else {
            System.out.println("Success");
            return new CommandResult(true, new GetReservationByUserResultData(list));
        }
    }
}
