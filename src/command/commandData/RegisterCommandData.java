package command.commandData;

import model.commandModels.CommandType;

import java.io.Serializable;

public class RegisterCommandData extends ServerCommandData implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;

    public RegisterCommandData(String username, String password, String email, String phone) {
        setCommandType(CommandType.REGISTER);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}

