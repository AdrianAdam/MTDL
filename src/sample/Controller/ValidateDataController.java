package sample.Controller;

import javafx.scene.control.Label;
import sample.Manager.DatabaseManager;
import sample.Model.User;

import java.sql.SQLException;
import java.util.List;

public class ValidateDataController {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();

    public ValidateDataController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public boolean validateNameEmail(String name, String email, Label error) {
        if(email.equals("") || name.equals("")) {
            error.setText(error.getText() + "\nName or email can't be empty");
        }

        List<User> users = databaseManager.selectAllUsers();
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getEmail().equals(email) && users.get(i).getUsername().equals(name)) {
                error.setText(error.getText() + "\nIncorrect name or email");
                return false;
            }
        }

        return true;
    }

    public boolean validateName(String name, Label error) {
        if(name.equals("")) {
            error.setText(error.getText() + "\nName can't be empty");
        }

        boolean ok = false;
        List<User> users = databaseManager.selectAllUsers();
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(name)) {
                ok = true;
            }
        }

        if(ok) {
            return true;
        } else {
            error.setText(error.getText() + "\nIncorrect name");
            return false;
        }
    }

    public boolean validatePassword(String password, Label error) {
        if(password.length() < 6) {
            error.setText(error.getText() + "\nPassword must have at least 6 characters");
            return false;
        }

        return true;
    }
}
