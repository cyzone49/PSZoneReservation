package database.DataAccess;

import model.Reservation;
import utils.UniqueChecker;
import utils.UniqueGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {


    private static final String CREATE_RESERVATIONS_TABLE =
            "create table if not exists RESERVATIONS" +
                    "(" +
                    "startTime INT NOT NULL," +
                    "duration REAL NOT NULL," +
                    "userID INT NOT NULL," +
                    "slotID INT NOT NULL, " +
                    "checkedIn INT NOT NULL," +
                    "confirmationID VARCHAR(255) NOT NULL" +
                    ");";

    private static final String SELECT_ALL_RESERVATIONS =
            "select rowid, * from RESERVATIONS";


    private static final String INSERT_INTO_RESERVATIONS_TABLE =
            "insert into RESERVATIONS (startTime, duration, userID, slotID, checkedIn, confirmationID)" +
                    " values (?,?,?,?,?,?);";

    private Reservation grabReservation(ResultSet rs) {
        try {
            Reservation reservation = new Reservation();
            reservation.setReservationID(rs.getInt("rowid"));
            reservation.setUserID(rs.getInt("userID"));
            reservation.setSlotID(rs.getInt("slotID"));
            reservation.setStartTime(rs.getInt("startTime"));
            reservation.setDuration(rs.getDouble("duration"));
            reservation.setCheckedIn(rs.getInt("checkedIn"));
            reservation.setUsername(rs.getString("username"));
            reservation.setPackageName(rs.getString("packageName"));
            reservation.setHourlyRate(rs.getDouble("hourlyRate"));
            reservation.setMaxOccupancy(rs.getInt("maxOccupancy"));
            reservation.setTotal(reservation.getDuration() * reservation.getHourlyRate());
            reservation.setConfirmationID(rs.getString("confirmationID"));

            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addReservation(Connection connection, Reservation reservation) {
        Statement statement = null;
        String confirmationID = UniqueGenerator.generate("");
        System.out.println(" --- confirmationID = " + confirmationID);

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_RESERVATIONS_TABLE);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_RESERVATIONS_TABLE);

            preparedStatement.setInt(1, reservation.getStartTime());
            preparedStatement.setDouble(2, reservation.getDuration());
            preparedStatement.setInt(3, reservation.getUserID());
            preparedStatement.setInt(4, reservation.getSlotID());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, confirmationID);

            preparedStatement.addBatch();
            preparedStatement.executeBatch();

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
        }
        return confirmationID;
    }

    public Reservation getReservationByID(Connection connection, String confirmationID) {
        Statement statement = null;
        ResultSet rs = null;
        Reservation reservation = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_RESERVATIONS_TABLE);

            String GET_RESERVATION_BY_ID =
                    "select RESERVATIONS.rowid, RESERVATIONS.*, USERS.username, PACKAGES.*" +
                    " from RESERVATIONS" +
                    " inner join USERS on RESERVATIONS.userID = USERS.rowid" +
                    " inner join SLOTS on RESERVATIONS.slotID = SLOTS.rowid" +
                    " inner join PACKAGES on SLOTS.packageID = PACKAGES.rowid" +
                    " where RESERVATIONS.confirmationID = \'" + confirmationID + "\'";

            System.out.println("\n\n\n");
            System.out.println(GET_RESERVATION_BY_ID);

            rs = statement.executeQuery(GET_RESERVATION_BY_ID);

            if(!rs.next()) {
                System.out.println("\tNo matching reservation data available");
                return null;
            }

            reservation = grabReservation(rs);

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
        return reservation;
    }

    public List<Reservation> getReservationByUser(Connection connection, int userID) {
        Statement statement = null;
        ResultSet rs = null;

        List<Reservation> list;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_RESERVATIONS_TABLE);

            String GET_RESERVATION_BY_USER =
                    "select reservations.rowid, reservations.*, users.username, packages.*" +
                    " from reservations" +
                    " inner join users on reservations.userID = users.rowid" +
                    " inner join slots on reservations.slotID = slots.rowid" +
                    " inner join packages on slots.packageID = packages.rowid" +
                    " where reservations.userID = " + userID;

            rs = statement.executeQuery(GET_RESERVATION_BY_USER);

            if(!rs.next()) {
                System.out.println("\tNo matching reservation data available");
                return null;
            }

            list = new ArrayList<>();
            list.add(grabReservation(rs));
            while(rs.next()) {
                list.add(grabReservation(rs));
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
        return list;
    }

    public boolean deleteReservation(Connection connection, int confirmationID) {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_RESERVATIONS_TABLE);

            String DELETE_RESERVATION = "delete from RESERVATIONS where confirmationID = " + confirmationID;

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION);

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
}
