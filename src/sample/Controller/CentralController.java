/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/12/19 7:54 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;

public class CentralController {

    private LayoutController layoutController = new LayoutController();
    private DatabaseManager databaseManager = DatabaseManager.getInstance();

    public CentralController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void scenarios(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("scenarioLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void robotManagement(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("robotManagementLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void editUser(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("editUserLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void signout(ActionEvent actionEvent) throws IOException {
        databaseManager.currentUser = null;
        layoutController.openWindow("loginLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }
}
