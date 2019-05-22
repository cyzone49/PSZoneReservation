package command.commandResult;

import model.commandModels.CommandType;
import model.Reservation;

import java.util.List;

public class GetReservationByUserResultData extends ClientResultData {

    private List<Reservation> reservationList;

    public GetReservationByUserResultData(List<Reservation> reservationList) {
        setCommandType(CommandType.GET_RESERVATION_BY_USER);
        this.reservationList = reservationList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
