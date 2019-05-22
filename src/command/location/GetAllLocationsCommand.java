package command.location;

import command.commandData.GetAllLocationsCommandData;
import model.commandModels.CommandResult;
import service.GetAllLocationsService;

import java.io.Serializable;

public class GetAllLocationsCommand implements Serializable {
    private GetAllLocationsCommandData commandData;

    public GetAllLocationsCommand(GetAllLocationsCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        GetAllLocationsService service = new GetAllLocationsService();
        return service.getAllLocations(this.commandData);
    }
}
