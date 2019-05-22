package command.commandResult;

import model.Location;
import model.commandModels.CommandType;

import java.util.List;

public class GetAllLocationsResultData extends ClientResultData {
    private List<Location> locations = null;

    public GetAllLocationsResultData(List<Location> locations) {
        setCommandType(CommandType.GET_LOCATIONS);
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
