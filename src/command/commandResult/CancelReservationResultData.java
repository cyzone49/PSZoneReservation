package command.commandResult;

import model.commandModels.CommandType;

public class CancelReservationResultData extends ClientResultData {
    private String confirmationID;

    public CancelReservationResultData(String confirmationID) {
        setCommandType(CommandType.CANCEL_RESERVATION);
        this.confirmationID = confirmationID;
    }

    public String getConfirmationID() {
        return confirmationID;
    }

    public void setConfirmationID(String confirmationID) {
        this.confirmationID = confirmationID;
    }
}
