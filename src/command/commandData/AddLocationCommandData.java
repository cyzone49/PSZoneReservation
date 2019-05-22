package command.commandData;

import model.commandModels.CommandType;

import java.io.Serializable;

public class AddLocationCommandData extends ServerCommandData implements Serializable {
    private String locationName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;

    public AddLocationCommandData(String locationName, String streetaddress, String city, String state, String zipcode) {
        setCommandType(CommandType.ADD_LOCATION);
        this.locationName = locationName;
        this.streetAddress = streetaddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }


    public String getLocationName() {
        return locationName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }
}
