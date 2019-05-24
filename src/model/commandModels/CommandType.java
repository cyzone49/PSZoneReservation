package model.commandModels;

public enum CommandType
{
    // User
    REGISTER, LOGIN,

    // Locations
    ADD_LOCATION, GET_LOCATIONS,

    // Packages
    ADD_PACKAGE, GET_PACKAGES,

    // slots
    ADD_SLOT, GET_AVAILABLE_SLOTS,

    // Reservation
    MAKE_RESERVATION, GET_RESERVATION_BY_ID, GET_RESERVATION_BY_USER,
    CANCEL_RESERVATION,

    // delete
    CLEAR,

    ERROR,

}
