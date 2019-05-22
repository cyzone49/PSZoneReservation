package model.commandModels;

import command.commandResult.ClientResultData;

public class CommandResult {

    private boolean success;
    private ClientResultData commandResult;

    public CommandResult(boolean success, ClientResultData commandResult) {
        this.success = success;
        this.commandResult = commandResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public ClientResultData getCommandResult() {
        return commandResult;
    }

    public String toString()
    {
        String retString = "";
        if(success)
        {
            retString = retString + "SUCCESS: True\n";
        }
        else
        {
            retString = retString + "SUCCESS: False\n";
        }

        retString = retString + "CommandResult: " + commandResult.getCommandType().toString() + "\n";
//        retString = retString + getCommandDataList().toString();

        return retString;
    }

}
