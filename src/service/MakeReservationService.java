package service;

import command.commandData.MakeReservationCommandData;
import model.commandModels.CommandResult;
import command.commandResult.ErrorResultData;
import command.commandResult.MakeReservationResultData;
import database.Database;
import model.Reservation;

public class MakeReservationService {

    public CommandResult makeReservation(MakeReservationCommandData commandData) {
        Database db = new Database();

        // check if user is marked down -- not allowed to make reservation
        db.openConnection();
        int markedDown = db.checkUserMarkedDown(commandData.getUserID());
        db.closeConnection(false);
        if(markedDown == 1) {
            System.out.println("\tNOT ALLOWED. User is marked down and must debt");
            return new CommandResult(false, new ErrorResultData("NOT ALLOWED. User is marked down and must settle owed amount"));
        } else if (markedDown == -1) {
            System.out.println("\tFAILED. No user data found in database");
            return new CommandResult(false, new ErrorResultData("FAILED. Could not find user data matching userID"));
        }

        // check if slot is available  -- cannot reserve an unavailable slot
        db.openConnection();
        int available = db.checkSlotAvailability(commandData.getSlotID());
        db.closeConnection(false);
        if(available == 0) {
            System.out.println("\tNOT ALLOWED. Slot is unavailable");
            return new CommandResult(false, new ErrorResultData("NOT ALLOWED. Slot is unavailable"));
        } else if (markedDown == -1) {
            System.out.println("\tFAILED. No slot data found in database");
            return new CommandResult(false, new ErrorResultData("FAILED. Could not find slot matching slotID in database"));
        }


        db.openConnection();

        Reservation reservation = new Reservation();
        reservation.setUserID(commandData.getUserID());
        reservation.setSlotID(commandData.getSlotID());
        reservation.setStartTime(commandData.getStartTime());
        reservation.setDuration(commandData.getDuration());

        String confirmation = db.insertReservation(reservation);

        if (confirmation != null) {
            System.out.println("\tSuccessfully added reservation to database");

            boolean success = db.markSlotUnavailable(commandData.getSlotID());
            db.closeConnection(success);

            if(success) {
                System.out.println("\t\tReservation Made. Operation is success!");
                return new CommandResult(true, new MakeReservationResultData(confirmation));
            } else {
                System.out.println("\t\tFailed to check slot Unavailable. No changes made to Database");
                return new CommandResult(false, new ErrorResultData("Failed to check slot Unavailable. No changes made to Database"));
            }

        } else {
            System.out.println("\tFailed to add reservation to database");
            db.closeConnection(false);
            return new CommandResult(false, new ErrorResultData("Failed to add reservation to database"));
        }

    }
}
