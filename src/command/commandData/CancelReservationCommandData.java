package command.commandData;

import model.commandModels.CommandType;

public class CancelReservationCommandData extends ServerCommandData {
    private int confirmationID;

    public CancelReservationCommandData(int confirmationID) {
        setCommandType(CommandType.CANCEL_RESERVATION);
        this.confirmationID = confirmationID;
    }

    public int getConfirmationID() {
        return confirmationID;
    }
}
