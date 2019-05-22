package command.commandResult;

import model.commandModels.CommandType;

public class RegisterResultData extends ClientResultData {
    private String sessionID;

    public RegisterResultData(String sessionID) {
        setCommandType(CommandType.REGISTER);
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
