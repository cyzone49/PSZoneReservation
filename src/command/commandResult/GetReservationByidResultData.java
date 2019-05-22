package command.commandResult;

import model.commandModels.CommandType;
import model.Reservation;

public class GetReservationByidResultData extends ClientResultData {
    private Reservation reservation;

    public GetReservationByidResultData(Reservation reservation) {
        setCommandType(CommandType.GET_RESERVATION_BY_ID);
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
