package database.DataAccess;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String CREATE_USERS_TABLE =
                    "create table if not exists USERS" +
                    "(" +
                    "username VARCHAR(255) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL, " +
                    "phone VARCHAR(255) NOT NULL, " +
                    "marked INT" +
                    ");";


    private static final String INSERT_INTO_USERS_TABLE =
            "insert into USERS (username, password, email, phone, marked) values (?,?,?,?,?);";

    private static final String SELECT_ALL_USERS =
            "select rowid, * from USERS";

    private User grabUser(ResultSet rs) {
        try {
            User user = new User();
            user.setUserID(rs.getInt("rowid"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            user.setMarked(rs.getInt("marked"));

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add a User object to DB
     * @param connection current SQL connection to DB
     * @param user User object to be added to DB
     * @return true if success, false otherwise
     */
    public boolean addUser(Connection connection, User user) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_USERS_TABLE);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_TABLE);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setInt(5, user.getMarked());

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


    public User getUserForLogin(Connection connection, String username, String password) {
        Statement statement = null;
        ResultSet rs = null;
        User user  = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_USERS_TABLE);

            String FIND_USER_FOR_LOGIN = "select rowid, * from USERS " +
                                        "where username = \'" + username + "\'" +
                                        "and password = \'" + password + "\'";

            rs = statement.executeQuery(FIND_USER_FOR_LOGIN);

            if(!rs.next()) {
                System.out.println("\tNo matching user data available");
                return null;
            }

            user = grabUser(rs);

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
        return user;
    }

    public int getMarkedDown(Connection connection, int userID) {
        Statement statement = null;
        ResultSet rs = null;
        int marked;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_USERS_TABLE);

            String FIND_USER_FOR_MARKED = "select marked from users where rowid = " + userID;

            rs = statement.executeQuery(FIND_USER_FOR_MARKED);

            if(!rs.next()) {
                System.out.println("\tNo User data matching userID");
                return -1;
            }

            marked = rs.getInt("marked");

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
        return marked;
    }

    /**
     * Get users from relational DB
     * @param connection
     * @return list of users from DB
     */
    public List<User> getUsers(Connection connection) {
        Statement statement = null;
        ResultSet rs = null;
        List<User> usersList = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_USERS_TABLE);

            rs = statement.executeQuery(SELECT_ALL_USERS);

            if (!rs.next()) {
                System.out.println("\tRelationalUsersDao: No user in DB");
                return null;
            }

            usersList = new ArrayList<>();
            usersList.add(grabUser(rs));
            while(rs.next()) {
                usersList.add(grabUser(rs));
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
        return usersList;
    }

    public void printExistingUsers(Connection connection){
        List<User> u = getUsers(connection);
        if(u!=null) {
            System.out.println("\t\tprinting existing Users: ");
            if(u.size() == 0) {
                System.out.println("\t\tNO RECORDS in USERS table");
            } else {
                for(User su : u) {
                    System.out.println("\t\t - " + su.getUsername());
                }
            }
            System.out.println();
        } else {
            System.out.println("\t\tEMPTY USERS DB!");
        }
    }

    public boolean clearUsers() {
        return false;
    }


}
