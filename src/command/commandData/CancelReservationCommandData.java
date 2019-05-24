package command.commandData;

import model.commandModels.CommandType;

public class CancelReservationCommandData extends ServerCommandData {
    private String id;
//    private String username;

    public CancelReservationCommandData(String confirmationID) {
        setCommandType(CommandType.CANCEL_RESERVATION);
        this.id = confirmationID;
    }

    public String getConfirmationID() {
        return this.id;
    }

}
