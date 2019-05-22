package command.commandResult;

import model.commandModels.CommandType;

public class AddLocationResultData extends ClientResultData {
    public AddLocationResultData() {
        setCommandType(CommandType.ADD_LOCATION);
    }
}
