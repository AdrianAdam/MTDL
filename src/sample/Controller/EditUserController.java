/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/16/19 2:19 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.User;
import sample.Validators.ValidateUserData;

import java.io.IOException;
import java.sql.SQLException;

public class EditUserController {
    public TextField usernameText;
    public TextField emailText;
    public TextField passwordText;
    public Label roleText;
    public Label noRobotsText;
    public Label editError;
    public Label editSuccess;
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private LayoutController layoutController = new LayoutController();
    private ValidateUserData validateUserData = new ValidateUserData();

    public EditUserController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void saveEdit(ActionEvent actionEvent) throws SQLException {
        editError.setText("");

        if(validateUserData.validatePassword(passwordText.getText(), editError)
                && validateUserData.validateNameEmailEdit(databaseManager.currentUser, usernameText.getText(), emailText.getText(), editError)) {
            User user = new User(databaseManager.currentUser.getId(), usernameText.getText(),
                    roleText.getText(), passwordText.getText(), emailText.getText(), Integer.parseInt(noRobotsText.getText()));
            databaseManager.updateUser(user);
            getUser(actionEvent);

            editSuccess.setText("Successfully changed the user details");
        }
    }

    public void getUser(ActionEvent actionEvent) {
        usernameText.setText(databaseManager.currentUser.getUsername());
        emailText.setText(databaseManager.currentUser.getEmail());
        passwordText.setText(databaseManager.currentUser.getPassword());
        roleText.setText(databaseManager.currentUser.getRole());
        noRobotsText.setText(databaseManager.currentUser.getNoRobots() + "");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }
}
