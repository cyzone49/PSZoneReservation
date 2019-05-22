package service;

import command.commandData.RegisterCommandData;
import model.commandModels.CommandResult;
import command.commandResult.ErrorResultData;
import command.commandResult.RegisterResultData;
import database.Database;
import model.User;

public class RegisterService {

    public CommandResult register(RegisterCommandData commandData) {
        System.out.println("RegisterService running...");
        Database db = new Database();

        db.openConnection();
        User user = new User(commandData.getUsername(),
                commandData.getPassword(),
                commandData.getEmail(),
                commandData.getPhone());

        boolean success = db.insertUser(user);
        db.closeConnection(success);

        if (success) {
            System.out.println("\tSUCCESS. New User inserted into DB");
            return new CommandResult(true, new RegisterResultData(user.getUsername()+user.getUserID()));

        } else {
            System.out.println("\tFAILED. User NOT inserted into DB");
            return new CommandResult(false, new ErrorResultData("Register failed! username/password combination not found"));
        }
    }

}
