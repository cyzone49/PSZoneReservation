package command.commandData;

import model.commandModels.CommandType;

public class GetReservationByIDCommandData extends ServerCommandData {
    private String id;

    public GetReservationByIDCommandData(String id) {
        setCommandType(CommandType.GET_RESERVATION_BY_ID);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
