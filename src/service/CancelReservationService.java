package service;

import command.commandData.CancelReservationCommandData;
import command.commandResult.CancelReservationResultData;
import command.commandResult.ErrorResultData;
import database.Database;
import model.Reservation;
import model.commandModels.CommandResult;

public class CancelReservationService {

    public CommandResult cancelReservation(CancelReservationCommandData commandData) {
        Database db = new Database();

        // check reservation existence
        db.openConnection();
        Reservation reservation = db.getReservationByID(commandData.getConfirmationID());
        db.closeConnection(false);

        if(reservation == null) {
            System.out.println("\tFAILED. No reservation found with matching ID in database");
            return new CommandResult(false, new ErrorResultData("No reservation found with matching ID in database"));
        }
        // TODO: check if startTime has already passed for Reservation Cancellation


        // ALL LEGAL. start reservation cancellation process
        db.openConnection();
        boolean freeSlot = db.markSlotAvailable(reservation.getSlotID());
        boolean deleted = db.deleteReservation(commandData.getConfirmationID());
        db.closeConnection(deleted && freeSlot);

        if(!freeSlot) {
            System.out.println("\tFAILED to free reserved slot from database");
            return new CommandResult(false, new ErrorResultData("Failed to free reserved slot from database"));
        }
        if(!deleted) {
            System.out.println("\tFAILED to delete reservation " + commandData.getConfirmationID() + " from database");
            return new CommandResult(false, new ErrorResultData("Failed to delete reservation "
                    + commandData.getConfirmationID() + "from database"));
        } else {
            System.out.println("\tSUCCESS. Reservation " + commandData.getConfirmationID() + " is deleted from Database");
            return new CommandResult(true, new CancelReservationResultData());
        }

    }
}
