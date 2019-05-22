package service;

import command.commandData.GetReservationByIDCommandData;
import command.commandResult.ErrorResultData;
import command.commandResult.GetReservationByidResultData;
import database.Database;
import model.Reservation;
import model.commandModels.CommandResult;

public class GetReservationByIDService {

    public CommandResult getReservationByID(GetReservationByIDCommandData commandData) {
        Database db = new Database();

        db.openConnection();
        Reservation reservation = db.getReservationByID(commandData.getId());
        db.closeConnection(false);

        if(reservation == null) {
            System.out.println("\tFailed to find reservation by input id");
            return new CommandResult(false, new ErrorResultData("Failed to find reservation by input id"));
        } else {
            System.out.println("\tSuccess");
            return new CommandResult(true, new GetReservationByidResultData(reservation));
        }
    }
}
