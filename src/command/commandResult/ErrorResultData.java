package command.commandResult;

import model.commandModels.CommandType;

public class ErrorResultData extends ClientResultData {
    private String msg = "";

    public ErrorResultData(String msg) {
        setCommandType(CommandType.ERROR);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
