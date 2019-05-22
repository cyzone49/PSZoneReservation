package model;

import utils.UniqueChecker;
import utils.UniqueGenerator;

import java.io.Serializable;
import java.util.Observable;

public class User extends Observable implements Serializable {
    private int userID;
    private String username;
    private String password;
    private String authtoken;
    private String email;
    private String phone;
    private int marked;

    /**
     * Official constructor used for Register operation
     * @param username
     * @param password
     */
    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.authtoken = generateAuthToken();
        this.email = email;
        this.phone = phone;
        this.marked = 0;
    }

    /**
     * Default constructor
     */
    public User() {}

    // Copy constructor, removes observers
    public User(User u) {
        this.username = u.username;
        this.password = u.password;
        this.authtoken = u.authtoken;
        this.deleteObservers();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
        setChanged();
        notifyObservers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMarked() {
        return marked;
    }

    public void setMarked(int marked) {
        this.marked = marked;
    }

    /**
     * Generate a new authToken that has not been assigned to a User in database
     * @return a new unique authToken
     */
    public String generateAuthToken(){
        return UniqueGenerator.generate(this.username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authtoken='" + authtoken + '\'' +
                '}' +
                '\n';
    }
}
