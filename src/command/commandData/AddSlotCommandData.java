package command.commandData;

import model.commandModels.CommandType;

public class AddSlotCommandData extends ServerCommandData {
    private int packageID;
    private int locationID;

    public AddSlotCommandData(int packageID, int locationID) {
        setCommandType(CommandType.ADD_SLOT);
        this.packageID = packageID;
        this.locationID = locationID;
    }

    public int getPackageID() {
        return packageID;
    }

    public int getLocationID() {
        return locationID;
    }
}
