package command.commandResult;

import model.commandModels.CommandType;

public class AddSlotResultData extends ClientResultData {
    public AddSlotResultData() {
        setCommandType(CommandType.ADD_SLOT);
    }
}
