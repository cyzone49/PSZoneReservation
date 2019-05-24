package database;

import database.DataAccess.*;
import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Database {
    private static final String JDBC_DRIVER_CLASS = "org.sqlite.JDBC";

    static {
        try {
            Class.forName(JDBC_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException();
        }
        return connection;
    }

    public void openConnection(){
        try {
            final String CONNECTION_URL = "jdbc:sqlite:RelationalDatabase.db";
            //Open a db connection
            connection = DriverManager.getConnection(CONNECTION_URL);
            //Start a transaction
            connection.setAutoCommit(false);
//            System.out.println(" ... Openned connection to sqlite DB ... \n");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(boolean commit){
        if (connection == null) {
            throw new IllegalStateException();
        }

        try {
            if (commit) {
                connection.commit();
            }
            else {
                connection.rollback();
            }

            connection.close();
            connection = null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * CREATE processes
     */
    public boolean insertUser(User user) {
        UserDao dao = new UserDao();
        return dao.addUser(connection, user);
    }

    public boolean insertLocation(Location location) {
        LocationDao dao = new LocationDao();
        return dao.addLocation(connection, location);
    }

    public boolean insertServicePackage(ServicePackage servicePackage) {
        ServicePackageDao dao = new ServicePackageDao();
        return dao.addServicePackage(connection, servicePackage);
    }

    public boolean insertSlot(Slot slot) {
        SlotDao dao = new SlotDao();
        return dao.addSlot(connection, slot);
    }

    public boolean markSlotAvailable(int slotID) {
        SlotDao dao = new SlotDao();
        return dao.changeSlotAvailability(connection, slotID, 1);
    }

    public boolean markSlotUnavailable(int slotID) {
        SlotDao dao = new SlotDao();
        return dao.changeSlotAvailability(connection, slotID, 0);
    }

    public String insertReservation(Reservation reservation) {
        ReservationDao dao = new ReservationDao();
        return dao.addReservation(connection, reservation);
    }

    /**
     * READ processes (select by something)
     */

    public User getUserForLogin(String username, String password) {
        UserDao dao = new UserDao();
        return dao.getUserForLogin(connection, username, password);
    }

    public int checkUserMarkedDown(int userID) {
        UserDao dao = new UserDao();
        return dao.getMarkedDown(connection, userID);
    }

    public List<Location> getAllLocations() {
        LocationDao dao = new LocationDao();
        return dao.getAllLocations(connection);
    }

    public List<ServicePackage> getAllServicePackages() {
        ServicePackageDao dao = new ServicePackageDao();
        return dao.getAllServicePackages(connection);
    }

    public List<Slot> getAvailableSlotsByLocation(int locationID) {
        SlotDao dao = new SlotDao();
        return dao.getAvailableSlotsByLocation(connection, locationID);
    }

    public List<Slot> getAvailableSlotsByPackage(int packageID) {
        SlotDao dao = new SlotDao();
        return dao.getAvailableSlotsByPackage(connection, packageID);
    }

    public List<Slot> getAvailableSlotsByLocationAndPackage(int locationID, int packageID) {
        SlotDao dao = new SlotDao();
        return dao.getAvailableSlotsByLocationAndPackage(connection, locationID, packageID);
    }

    public List<Slot> getAllAvailableSlots() {
        SlotDao dao = new SlotDao();
        return dao.getAllAvailableSlots(connection);
    }

    public int checkSlotAvailability(int slotID) {
        SlotDao dao = new SlotDao();
        return dao.checkSlotAvailability(connection, slotID);
    }

    public Reservation getReservationByID(String confirmationID) {
        ReservationDao dao = new ReservationDao();
        return dao.getReservationByID(connection, confirmationID);
    }

    public List<Reservation> getReservationByUser(int userID) {
        ReservationDao dao = new ReservationDao();
        return dao.getReservationByUser(connection, userID);
    }


    public boolean deleteReservation(String confirmationID) {
        ReservationDao dao = new ReservationDao();
        return dao.deleteReservation(connection, confirmationID);
    }


}
