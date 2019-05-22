package database.DataAccess;

import model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {
    private static final String CREATE_LOCATIONS_TABLE =
            "create table if not exists LOCATIONS" +
                    "(" +
                    "locationName VARCHAR(266) UNIQUE NOT NULL," +
                    "streetAddress VARCHAR(255) NOT NULL, " +
                    "city VARCHAR(255) NOT NULL, " +
                    "state VARCHAR(2) NOT NULL, " +
                    "zipcode VARCHAR(5) NOT NULL " +
                    ");";

    private static final String SELECT_ALL_LOCATIONS =
            "select rowid, * from LOCATIONS";


    private static final String INSERT_INTO_LOCATIONS_TABLE =
            "insert into LOCATIONS (locationName, streetAddress, city, state, zipcode) values (?,?,?,?,?);";

    public boolean addLocation(Connection connection, Location location) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_LOCATIONS_TABLE);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_LOCATIONS_TABLE);
            preparedStatement.setString(1, location.getLocationName());
            preparedStatement.setString(2, location.getStreetAddress());
            preparedStatement.setString(3, location.getCity());
            preparedStatement.setString(4, location.getState());
            preparedStatement.setString(5, location.getZipcode());

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

    /**
     * Get all locations from the relational database
     * @param connection
     * @return a list of locations
     */
    public List<Location> getAllLocations(Connection connection) {
        Statement statement = null;
        ResultSet rs = null;
        List<Location> locationsList = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_LOCATIONS_TABLE);

            rs = statement.executeQuery(SELECT_ALL_LOCATIONS);

            if (!rs.next()) {
                System.out.println("\tRelationalUsersDao: No user in DB");
                return null;
            }

            locationsList = new ArrayList<>();
            locationsList.add(grabData(rs));

            while(rs.next()) {
                locationsList.add(grabData(rs));

            }
//            if (locationsList.size()==0) {
//                System.out.println("\tRelationalUsersDao: No user in DB");
//                return null;
//            }
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
        return locationsList;
    }

    public Location getLcoationByID(Connection connection, int id) {
        Statement statement = null;
        ResultSet rs = null;
        Location location = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_LOCATIONS_TABLE);

            String SELECT_LOCATION_BY_ID =
                    "select rowid, * from LOCATIONS where rowid = " + id;

            rs = statement.executeQuery(SELECT_LOCATION_BY_ID);

            if (!rs.next()) {
                System.out.println("\tLocationDao: No location in DB");
                return null;
            }

            location = grabData(rs);

            if (location == null) {
                System.out.println("\tLocationDao: No location in DB");
                return null;
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
        return location;
    }

    private Location grabData(ResultSet rs) {
        try {
            Location location = new Location();
            location.setLocationID(rs.getInt("rowid"));
            location.setLocationName(rs.getString("locationName"));
            location.setStreetAddress(rs.getString("streetAddress"));
            location.setCity(rs.getString("city"));
            location.setState(rs.getString("state"));
            location.setZipcode(rs.getString("zipcode"));
            return location;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
