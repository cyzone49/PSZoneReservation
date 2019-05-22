package command.reservation;

import command.commandData.GetReservationByUserCommandData;
import model.commandModels.CommandResult;
import service.GetReservationByUserService;

import java.io.Serializable;

public class GetReservationByUserCommand implements Serializable {
    private GetReservationByUserCommandData commandData;

    public GetReservationByUserCommand(GetReservationByUserCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        GetReservationByUserService service = new GetReservationByUserService();
        return service.getReservationByUser(this.commandData);
    }
}
