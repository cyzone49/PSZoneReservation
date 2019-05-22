package command.servicePackage;

import command.commandData.AddServicePackageCommandData;
import model.commandModels.CommandResult;
import service.AddServicePackageService;

import java.io.Serializable;

public class AddServicePackageCommand implements Serializable {
    private AddServicePackageCommandData commandData;

    public AddServicePackageCommand(AddServicePackageCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        AddServicePackageService service = new AddServicePackageService();
        return service.addServicePackage(commandData);
    }
}
