package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.User;

import java.io.IOException;
import java.sql.SQLException;

public class EditUserController {
    public TextField usernameText;
    public TextField emailText;
    public TextField passwordText;
    public Label roleText;
    public Label noRobotsText;
    public Label editError;
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private LayoutController layoutController = new LayoutController();
    private ValidateUserDataController validateUserDataController = new ValidateUserDataController();

    public EditUserController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void saveEdit(ActionEvent actionEvent) throws SQLException {
        editError.setText("");

        if(validateUserDataController.validatePassword(passwordText.getText(), editError)
                && validateUserDataController.validateNameEmail(usernameText.getText(), emailText.getText(), editError)) {
            User user = new User(databaseManager.currentUser.getId(), usernameText.getText(),
                    roleText.getText(), passwordText.getText(), emailText.getText(), Integer.parseInt(noRobotsText.getText()));
            databaseManager.updateUser(user);
            getUser(actionEvent);
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
