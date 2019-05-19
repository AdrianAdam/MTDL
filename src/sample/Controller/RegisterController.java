/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/16/19 2:22 PM.
 * Copyright (c) 2019. All rights reserved.
 */

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

    public void registerUser(String username, String password, String email, Label error, Label success) throws SQLException {
        if(validateUserData.validatePassword(password, error)
                && validateUserData.validateNameEmail(username, email, error)) {
            databaseManager.insertNewUser(username, password, email);
            success.setText(success.getText() + "\nYou have successfully created a new account");
        }
    }
}
