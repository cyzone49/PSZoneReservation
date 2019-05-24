package model.commandModels;

import command.commandResult.ClientResultData;

public class CommandResult {

    private boolean success;
    private ClientResultData resultData;

    public CommandResult(boolean success, ClientResultData commandResult) {
        this.success = success;
        this.resultData = commandResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public ClientResultData getCommandResult() {
        return resultData;
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

        retString = retString + "ResultData: " + resultData.getCommandType().toString() + "\n";
//        retString = retString + getCommandDataList().toString();

        return retString;
    }

}
