package database.DataAccess;

import model.ServicePackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePackageDao {
    private static final String CREATE_PACKAGES_TABLE =
            "create table if not exists PACKAGES" +
                    "(" +
                    "packageName VARCHAR(255) UNIQUE NOT NULL," +
                    "hourlyRate REAL NOT NULL, " +
                    "maxOccupancy INT NOT NULL " +
                    ");";

    private static final String SELECT_ALL_PACKAGES =
            "select rowid as \'packageID\', * from PACKAGES";

    private static final String INSERT_INTO_PACKAGES_TABLE =
            "insert into PACKAGES (packageName, hourlyRate, maxOccupancy) values (?,?,?);";


    public boolean addServicePackage(Connection connection, ServicePackage servicePackage) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_PACKAGES_TABLE);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PACKAGES_TABLE);
            preparedStatement.setString(1, servicePackage.getPackageName());
            preparedStatement.setDouble(2, servicePackage.getHourlyRate());
            preparedStatement.setInt(3, servicePackage.getMaxOccupancy());

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

    public List<ServicePackage> getAllServicePackages(Connection connection) {
        Statement statement = null;
        ResultSet rs = null;
        List<ServicePackage> list = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_PACKAGES_TABLE);

            rs = statement.executeQuery(SELECT_ALL_PACKAGES);

            if(!rs.next()) {
                System.out.println("\tNo ServicePackage found in database");
                return null;
            }

            list = new ArrayList<>();
            list.add(grabPackage(rs));
            while(rs.next()) {
                list.add(grabPackage(rs));
            }
        }
        catch (Exception e) {
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

    private ServicePackage grabPackage(ResultSet rs) {
        try {
            ServicePackage sp = new ServicePackage();
            sp.setPackageID(rs.getInt("packageID"));
            sp.setPackageName(rs.getString("packageName"));
            sp.setHourlyRate(rs.getDouble("hourlyRate"));
            sp.setMaxOccupancy(rs.getInt("maxOccupancy"));

            return sp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
