package command.commandData;

import model.commandModels.CommandType;

public class GetAllLocationsCommandData extends ServerCommandData {
    public GetAllLocationsCommandData() {
        setCommandType(CommandType.GET_LOCATIONS);
    }
}
