package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.Robot;

import java.io.IOException;
import java.sql.SQLException;

public class RobotManagementController {
    public TextField nameCreateText;
    public TextField typeCreateText;
    public TextField iconCreateText;
    public TextField imageCreateText;
    public TextField connectivityCreateText;
    public Label editError;
    public Label createError;
    public TextField nameEditText;
    public TextField typeEditText;
    public TextField iconEditText;
    public TextField imageEditText;
    public TextField connectivityEditText;
    public TextField nameDeleteText;
    public Label deleteError;

    private LayoutController layoutController = new LayoutController();
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private ValidateRobotDataController validateRobotDataController = new ValidateRobotDataController();

    private Robot currentRobotToEdit = null;

    public RobotManagementController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void saveEdit(ActionEvent actionEvent) throws SQLException {
        if (validateRobotDataController.validateNameExists(nameEditText.getText())
                && validateRobotDataController.validateAllData(nameEditText.getText(), typeEditText.getText(),
                iconEditText.getText(), imageEditText.getText(), connectivityEditText.getText(), editError)) {
            databaseManager.updateRobot(nameEditText.getText(), currentRobotToEdit.getState(), currentRobotToEdit.getCoordX(),
                    currentRobotToEdit.getCoordY(), typeEditText.getText(), iconEditText.getText(), imageEditText.getText(), connectivityEditText.getText());
        }
    }

    public void searchEdit(ActionEvent actionEvent) throws SQLException {
        if (validateRobotDataController.validateNameExists(nameEditText.getText())) {
            Robot robot = new Robot();
            robot = databaseManager.selectOneRobot(nameEditText.getText());

            typeEditText.setText(robot.getType());
            iconEditText.setText(robot.getIcon());
            imageEditText.setText(robot.getImage());
            connectivityEditText.setText(robot.getConnectivity());

            currentRobotToEdit = robot;
        }
    }

    public void saveCreate(ActionEvent actionEvent) throws SQLException {
        if (validateRobotDataController.validateAllData(nameCreateText.getText(), typeCreateText.getText(), iconCreateText.getText(),
                imageCreateText.getText(), connectivityCreateText.getText(), createError)
                && !validateRobotDataController.validateNameExists(nameCreateText.getText())
                && validateRobotDataController.validateNumberRobots()) {
            databaseManager.insertNewRobot(nameCreateText.getText(), "idle", 0, 0, typeCreateText.getText(),
                    iconCreateText.getText(), imageCreateText.getText(), connectivityCreateText.getText());
        }
    }

    public void deleteRobot(ActionEvent actionEvent) throws SQLException {
        if (validateRobotDataController.validateName(nameDeleteText.getText())
                && validateRobotDataController.validateNameExists(nameDeleteText.getText())) {
            databaseManager.deleteRobot(nameDeleteText.getText());
        }
    }
}
