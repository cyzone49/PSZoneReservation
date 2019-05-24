package service;

import command.commandData.LoginCommandData;
import model.commandModels.CommandResult;
import command.commandResult.ErrorResultData;
import command.commandResult.LoginResultData;
import database.Database;
import model.User;

public class LoginService {

    public CommandResult login(LoginCommandData commandData) {
        Database db = new Database();

        db.openConnection();
        User user = db.getUserForLogin(commandData.getUsername(), commandData.getPassword());
        db.closeConnection(false);

        if(user == null) {
            System.out.println("\tFailed! cannot load loggin user's info");
            return new CommandResult(false, new ErrorResultData("Login failed! No match!"));
        } else {
            System.out.println("\tSuccessful! loaded user's info. {" + user.getUsername() +"}");
            LoginResultData data = new LoginResultData();
            data.setUserID(user.getUserID());
            data.setUsername(user.getUsername());
            data.setPassword(user.getPassword());
            data.setEmail(user.getEmail());
            data.setPhone(user.getPhone());

            return new CommandResult(true, data);
        }

    }
}
