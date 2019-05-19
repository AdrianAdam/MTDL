/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/16/19 10:30 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.User;
import sample.Validators.ValidateUserData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private LayoutController layoutController = new LayoutController();
    private ValidateUserData validateUserData = new ValidateUserData();

    public LoginController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void loginUser(String username, String password, ActionEvent actionEvent, Label error) throws IOException {
        if(validateUserData.validateName(username, error) && validateUserData.validatePassword(password, error)) {
            List<User> users;
            users = databaseManager.selectAllUsers();

            for(int i = 0; i < users.size(); i++) {
                if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                    databaseManager.currentUser = users.get(i);

                    layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
                }
            }
        }
    }
}
