package command.user;

import command.commandData.LoginCommandData;
import model.commandModels.CommandResult;
import service.LoginService;
import java.io.Serializable;

public class LoginCommand implements Serializable {
    private LoginCommandData sLoginCommandData;

    public LoginCommand(LoginCommandData sLoginCommandData) {
        this.sLoginCommandData = sLoginCommandData;
    }

    public CommandResult execute(){
        LoginService service = new LoginService();
        return service.login( this.sLoginCommandData );
    }

}

