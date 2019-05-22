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
            return new CommandResult(true, new LoginResultData(user.getUsername()+user.getUserID()));
        }

    }
}
