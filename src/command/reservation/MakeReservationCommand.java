package command.reservation;

import command.commandData.MakeReservationCommandData;
import model.commandModels.CommandResult;
import service.MakeReservationService;

public class MakeReservationCommand {
    MakeReservationCommandData commandData;

    public MakeReservationCommand(MakeReservationCommandData commandData) {
        this.commandData = commandData;
    }

    public CommandResult execute() {
        MakeReservationService service = new MakeReservationService();
        return service.makeReservation(this.commandData);
    }
}
