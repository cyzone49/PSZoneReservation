package command.commandResult;

import model.commandModels.CommandType;

public class RegisterResultData extends ClientResultData {
    private String username;
    private String password;
    private int userID;
    private String email;
    private String phone;

    public RegisterResultData(String username, String password, int userID, String email, String phone) {
        setCommandType(CommandType.REGISTER);
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.email = email;
        this.phone = phone;
    }

    public RegisterResultData() {
        setCommandType(CommandType.REGISTER);
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
}
