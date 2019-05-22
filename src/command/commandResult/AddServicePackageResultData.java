package command.commandResult;

import model.commandModels.CommandType;

public class AddServicePackageResultData extends ClientResultData {
    public AddServicePackageResultData() {
        setCommandType(CommandType.ADD_PACKAGE);
    }
}
