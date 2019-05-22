package command.location;

import command.commandData.AddLocationCommandData;
import model.commandModels.CommandResult;
import service.AddLocationService;

import java.io.Serializable;

public class AddLocationCommand implements Serializable {
    private AddLocationCommandData commandData;

    public AddLocationCommand(AddLocationCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        AddLocationService service = new AddLocationService();
        return service.addLocation( this.commandData );
    }
}
