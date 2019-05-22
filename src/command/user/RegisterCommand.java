package command.user;

import command.commandData.RegisterCommandData;
import model.commandModels.CommandResult;
import service.RegisterService;

import java.io.Serializable;

public class RegisterCommand implements Serializable {
    private RegisterCommandData commandData;

    public RegisterCommand(RegisterCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        RegisterService service = new RegisterService();
        return service.register( this.commandData );
    }

}
