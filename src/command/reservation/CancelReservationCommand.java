package command.reservation;

import command.commandData.CancelReservationCommandData;
import model.commandModels.CommandResult;
import service.CancelReservationService;

import java.io.Serializable;

public class CancelReservationCommand implements Serializable {
    private CancelReservationCommandData commandData;

    public CancelReservationCommand(CancelReservationCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        CancelReservationService service = new CancelReservationService();
        return service.cancelReservation(this.commandData);
    }
}
