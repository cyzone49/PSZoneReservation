package command.commandResult;

import model.commandModels.CommandType;

public class MakeReservationResultData extends ClientResultData{
    private String confirmationID;
    public MakeReservationResultData(String confirmationID) {
        setCommandType(CommandType.MAKE_RESERVATION);
        this.confirmationID = confirmationID;
    }

    public String getConfirmationID() {
        return confirmationID;
    }

    public void setConfirmationID(String confirmationID) {
        this.confirmationID = confirmationID;
    }
}
