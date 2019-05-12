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
