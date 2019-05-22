package service;

import command.commandData.CancelReservationCommandData;
import command.commandResult.CancelReservationResultData;
import command.commandResult.ErrorResultData;
import database.Database;
import model.commandModels.CommandResult;

public class CancelReservationService {

    public CommandResult cancelReservation(CancelReservationCommandData commandData) {
        Database db = new Database();

        db.openConnection();

        boolean success = db.deleteReservation(commandData.getConfirmationID());

        db.closeConnection(success);

        if(success) {
            System.out.println("\tSUCCESS. Reservation " + commandData.getConfirmationID() + " is deleted from Database");
            return new CommandResult(true, new CancelReservationResultData());
        } else {
            System.out.println("\tFAILED. Reservation " + commandData.getConfirmationID() + " NOT deleted");
            return new CommandResult(false, new ErrorResultData("Failed to delete reservation "
                    + commandData.getConfirmationID() + "from database"));
        }

    }
}
