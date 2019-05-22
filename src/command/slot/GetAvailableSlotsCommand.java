package command.slot;

import command.commandData.GetAvailableSlotsCommandData;
import model.commandModels.CommandResult;
import service.GetAvailableSlotsService;

import java.io.Serializable;

public class GetAvailableSlotsCommand implements Serializable {
    private GetAvailableSlotsCommandData commandData;

    public GetAvailableSlotsCommand(GetAvailableSlotsCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        GetAvailableSlotsService service = new GetAvailableSlotsService();
        return service.getAvailableSlots(this.commandData);
    }
}
