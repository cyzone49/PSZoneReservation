package command.commandData;

import java.io.Serializable;

import static model.commandModels.CommandType.LOGIN;

public class LoginCommandData extends ServerCommandData implements Serializable {
    private String username;
    private String password;

    public LoginCommandData(String username, String password) {
        setCommandType(LOGIN);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
