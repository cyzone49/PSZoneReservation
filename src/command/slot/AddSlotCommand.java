package command.slot;

import command.commandData.AddSlotCommandData;
import model.commandModels.CommandResult;
import service.AddSlotService;

import java.io.Serializable;

public class AddSlotCommand implements Serializable {
    private AddSlotCommandData commandData;

    public AddSlotCommand(AddSlotCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        AddSlotService service = new AddSlotService();
        return service.addSlot(this.commandData);
    }
}
