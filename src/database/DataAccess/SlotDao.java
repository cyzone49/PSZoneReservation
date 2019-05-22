package database.DataAccess;

import model.Slot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SlotDao {
    private static final String CREATE_SLOTS_TABLE =
            "create table if not exists SLOTS" +
                    "(" +
                    "locationID  INT NOT NULL," +
                    "packageID INT NOT NULL, " +
                    "availability INT NOT NULL" +
                    ");";

    private static final String SELECT_ALL_SLOTS =
            "select rowid as \'slotID\', * from SLOTS";

    private static final String INSERT_INTO_SLOTS_TABLE =
            "insert into SLOTS (locationID, packageID, availability) values (?,?,?);";

    private Slot grabSlot(ResultSet rs) {
        try {
            Slot slot = new Slot();
            slot.setSlotID(rs.getInt("rowid"));
            slot.setLocationID(rs.getInt("locationID"));
            slot.setPackageID(rs.getInt("packageID"));
            slot.setAvailability(rs.getInt("availability"));
            slot.setPackageName(rs.getString("packageName"));
            slot.setHourlyRate(rs.getDouble("hourlyRate"));
            slot.setMaxOccupancy(rs.getInt("maxOccupancy"));
            String address = rs.getString("streetAddress") + ", " +
                    rs.getString("city") + ", " +
                    rs.getString("state") + " " + rs.getString("zipcode");
            slot.setLocationAddress(address);
            return slot;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addSlot(Connection connection, Slot slot) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_SLOTS_TABLE);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SLOTS_TABLE);
            preparedStatement.setInt(1, slot.getLocationID());
            preparedStatement.setInt(2, slot.getPackageID());
            preparedStatement.setInt(3, slot.getAvailability());

            preparedStatement.addBatch();
            preparedStatement.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


    public boolean markSlotUnavailability(Connection connection, int slotID, int availability) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_SLOTS_TABLE);

            String MARK_SLOT_AVAILABILITY =
                    "update SLOTS set availability = " + availability +
                    " where rowid = " + slotID;

            PreparedStatement preparedStatement = connection.prepareStatement(MARK_SLOT_AVAILABILITY);

            preparedStatement.addBatch();
            preparedStatement.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private List<Slot> getAvailableSlots(Connection connection, String query) {
        Statement statement = null;
        ResultSet rs = null;
        List<Slot> slots= null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_SLOTS_TABLE);

            rs = statement.executeQuery(query);

            if(!rs.next()) {
                System.out.println("\tSlotsDao: No available slot!");
                return null;
            }

            slots = new ArrayList<>();
            slots.add(grabSlot(rs));

            while(rs.next()) {
                slots.add(grabSlot(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return slots;
    }

    public List<Slot> getAvailableSlotsByLocation(Connection connection, int locationID) {
        System.out.println("\tgetAvailableSlotsByLocation: locationID = " + locationID);
        String SELECT_SLOTS_BY_LOCATION =
                "select SLOTS.rowid, SLOTS.*, PACKAGES.*, LOCATIONS.* " +
                        " from SLOTS " +
                        " inner join LOCATIONS on SLOTS.locationID = LOCATIONS.rowid " +
                        " join PACKAGES on SLOTS.packageID = PACKAGES.rowid " +
                        " where SLOTS.availability = 1 " +
                        " and SLOTS.locationID = " + locationID;
        System.out.println("\n\nSQL:\n\t" + SELECT_SLOTS_BY_LOCATION + "\n\n");
        return getAvailableSlots(connection, SELECT_SLOTS_BY_LOCATION);
    }

    public List<Slot> getAvailableSlotsByPackage(Connection connection, int packageID) {
        System.out.println("\tgetAvailableSlotsByPackage: packageID = " + packageID);
        String SELECT_SLOTS_BY_PACKAGE =
                "select SLOTS.rowid, SLOTS.*, PACKAGES.*, LOCATIONS.* " +
                        " from SLOTS " +
                        " inner join LOCATIONS on SLOTS.locationID = LOCATIONS.rowid " +
                        " join PACKAGES on SLOTS.packageID = PACKAGES.rowid " +
                        " where SLOTS.availability = 1 " +
                        " and SLOTS.packageID = " + packageID;
        System.out.println("\n\nSQL:\n\t" + SELECT_SLOTS_BY_PACKAGE + "\n\n");
        return getAvailableSlots(connection, SELECT_SLOTS_BY_PACKAGE);
    }

    public List<Slot> getAvailableSlotsByLocationAndPackage(Connection connection, int locationID, int packageID) {
        System.out.println("\tgetAvailableSlotsByLocationAndPackage:");
        System.out.println("\t\tlocationID = " + locationID);
        System.out.println("\t\tpackageID = " + packageID);
        System.out.println("\n\n");
        String SELECT_SLOTS_BY_LOCATION_AND_PACKAGE =
                "select SLOTS.rowid, SLOTS.*, PACKAGES.*, LOCATIONS.* " +
                        " from SLOTS " +
                        " inner join LOCATIONS on SLOTS.locationID = LOCATIONS.rowid " +
                        " join PACKAGES on SLOTS.packageID = PACKAGES.rowid " +
                        " where SLOTS.availability = 1 " +
                        " and SLOTS.locationID = " + locationID +
                        " and SLOTS.packageID = " + packageID;
        System.out.println("\n\nSQL:\n\t" + SELECT_SLOTS_BY_LOCATION_AND_PACKAGE + "\n\n");
        return getAvailableSlots(connection, SELECT_SLOTS_BY_LOCATION_AND_PACKAGE);
    }

    public List<Slot> getAllAvailableSlots(Connection connection) {
        String SELECT_AVAILABLE_SLOTS =
                "select SLOTS.rowid, SLOTS.*, PACKAGES.*, LOCATIONS.* " +
                        " from SLOTS " +
                        " inner join LOCATIONS on SLOTS.locationID = LOCATIONS.rowid " +
                        " join PACKAGES on SLOTS.packageID = PACKAGES.rowid " +
                        " where SLOTS.availability = 1 ";
        System.out.println("\n\nSQL:\n\t" + SELECT_AVAILABLE_SLOTS + "\n\n");
        return getAvailableSlots(connection, SELECT_AVAILABLE_SLOTS);
    }

    public int checkSlotAvailability(Connection connection, int slotID) {

        Statement statement = null;
        ResultSet rs = null;
        int availability;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_SLOTS_TABLE);

            String CHECK_SLOT_AVAILABILITY =
                    "select availability from SLOTS where rowid = " + slotID;

            rs = statement.executeQuery(CHECK_SLOT_AVAILABILITY);

            if(!rs.next()) {
                System.out.println("\tNo slot matching input slotID found in database");
                return -1;
            }
            availability = rs.getInt("availability");

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return availability;
    }
}
