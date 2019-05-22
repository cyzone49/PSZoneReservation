package command.commandData;

import model.commandModels.CommandType;

public class GetReservationByUserCommandData extends ServerCommandData{
    private int userID;

    public GetReservationByUserCommandData(int userID) {
        setCommandType(CommandType.GET_RESERVATION_BY_USER);
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}
