package command.servicePackage;

import command.commandData.GetAllServicePackagesCommandData;
import model.commandModels.CommandResult;
import service.GetAllServicePackagesService;

public class GetAllServicePackagesCommand {
    private GetAllServicePackagesCommandData commandData;

    public GetAllServicePackagesCommand(GetAllServicePackagesCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        GetAllServicePackagesService service = new GetAllServicePackagesService();
        return service.getAllPackages(this.commandData);
    }
}
