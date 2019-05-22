package command.commandResult;

import model.commandModels.CommandType;

public class LoginResultData extends ClientResultData {
    private String sessionID;

    public LoginResultData(String sessionID) {
        setCommandType(CommandType.LOGIN);
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
