package command.commandData;

import model.commandModels.CommandType;

public class GetAvailableSlotsCommandData extends ServerCommandData {
    private int packageID;
    private int locationID;

    public GetAvailableSlotsCommandData(int locationID, int packageID) {
        setCommandType(CommandType.GET_AVAILABLE_SLOTS);
        this.locationID = locationID;
        this.packageID = packageID;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
}
