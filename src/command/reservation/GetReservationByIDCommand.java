package command.reservation;

import command.commandData.GetReservationByIDCommandData;
import model.commandModels.CommandResult;
import service.GetReservationByIDService;

import java.io.Serializable;

public class GetReservationByIDCommand implements Serializable {
    private GetReservationByIDCommandData commandData;

    public GetReservationByIDCommand(GetReservationByIDCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        GetReservationByIDService service = new GetReservationByIDService();
        return service.getReservationByID(this.commandData);
    }
}
