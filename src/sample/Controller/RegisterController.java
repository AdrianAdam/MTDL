package sample.Controller;

import javafx.scene.control.Label;
import sample.Manager.DatabaseManager;

import java.sql.SQLException;

public class RegisterController {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private ValidateDataController validateDataController = new ValidateDataController();

    public RegisterController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void registerUser(String username, String password, String email, Label error) throws SQLException {
        if(validateDataController.validatePassword(password, error)
                && validateDataController.validateNameEmail(username, email, error)) {
            databaseManager.insertNewUser(username, password, email);
        }
    }
}
