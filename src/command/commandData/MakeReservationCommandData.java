package command.commandData;

import model.commandModels.CommandType;

public class MakeReservationCommandData extends ServerCommandData {
    private int slotID;
    private int userID;

    private int startTime;
    private double duration;

    public MakeReservationCommandData(int slotID, int userID, int startTime, double duration) {
        setCommandType(CommandType.MAKE_RESERVATION);
        this.slotID = slotID;
        this.userID = userID;
        this.startTime = startTime;
        this.duration = duration;
    }

    public MakeReservationCommandData() {}

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
