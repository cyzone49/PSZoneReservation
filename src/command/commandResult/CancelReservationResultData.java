package command.commandResult;

import model.commandModels.CommandType;

public class CancelReservationResultData extends ClientResultData {
    public CancelReservationResultData() {
        setCommandType(CommandType.CANCEL_RESERVATION);
    }
}
