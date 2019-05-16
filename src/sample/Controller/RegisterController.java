package sample.Controller;

import javafx.scene.control.Label;
import sample.Manager.DatabaseManager;
import sample.Validators.ValidateUserData;

import java.sql.SQLException;

public class RegisterController {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private ValidateUserData validateUserData = new ValidateUserData();

    public RegisterController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void registerUser(String username, String password, String email, Label error) throws SQLException {
        if(validateUserData.validatePassword(password, error)
                && validateUserData.validateNameEmail(username, email, error)) {
            databaseManager.insertNewUser(username, password, email);
        }
    }
}
