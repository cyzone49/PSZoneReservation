package command.commandData;

import model.commandModels.CommandType;

import java.io.Serializable;

public class ServerCommandData implements Serializable {
    private CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }
}
