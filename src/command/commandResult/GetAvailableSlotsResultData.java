package command.commandResult;

import model.commandModels.CommandType;
import model.Slot;

import java.util.List;

public class GetAvailableSlotsResultData extends ClientResultData {
    private List<Slot> slots;

    public GetAvailableSlotsResultData(List<Slot> slots) {
        setCommandType(CommandType.GET_AVAILABLE_SLOTS);
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
