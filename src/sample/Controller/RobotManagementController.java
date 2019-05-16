package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.Robot;
import sample.Validators.ValidateRobotData;

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
    public Label deleteError;
    public TextField nameEditText;
    public TextField typeEditText;
    public TextField iconEditText;
    public TextField imageEditText;
    public TextField connectivityEditText;
    public TextField nameDeleteText;
    public ChoiceBox<String> imageCreateChoice;
    public ChoiceBox<String> imageEditChoice;
    public Label createSuccess;
    public Label editSuccess;
    public Label deleteSuccess;

    private LayoutController layoutController = new LayoutController();
    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private ValidateRobotData validateRobotData = new ValidateRobotData();

    private Robot currentRobotToEdit = null;

    public RobotManagementController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void saveEdit(ActionEvent actionEvent) throws SQLException {
        if (validateRobotData.validateNameExists(nameEditText.getText())
                && validateRobotData.validateAllData(nameEditText.getText(), typeEditText.getText(),
                iconEditText.getText(), imageEditText.getText(), connectivityEditText.getText(), editError)) {
            String path = "/home/adrianadam/Desktop/FILS/Anul 3/Sem 2/MTDL/MTDLProject/src/sample/Resources/" + imageEditChoice.getValue();
            databaseManager.updateRobot(nameEditText.getText(), currentRobotToEdit.getState(), currentRobotToEdit.getCoordX(),
                    currentRobotToEdit.getCoordY(), typeEditText.getText(), iconEditText.getText(), path, connectivityEditText.getText());

            editSuccess.setText(editSuccess.getText() + "\nSuccessfully edited the robot");
        }
    }

    public void searchEdit(ActionEvent actionEvent) throws SQLException {
        if (validateRobotData.validateNameExists(nameEditText.getText())) {
            Robot robot = databaseManager.selectOneRobot(nameEditText.getText());

            String[] path = robot.getImage().split("/");

            typeEditText.setText(robot.getType());
            iconEditText.setText(robot.getIcon());
            imageEditChoice.setValue(path[path.length - 1]);
            //imageEditText.setText(path[path.length - 1]);
            connectivityEditText.setText(robot.getConnectivity());

            currentRobotToEdit = robot;

            editSuccess.setText(editSuccess.getText() + "\nRobot found");
        } else {
            editError.setText(editError.getText() + "\nRobot doesn't exist");
        }
    }

    public void saveCreate(ActionEvent actionEvent) throws SQLException {
        if (validateRobotData.validateAllData(nameCreateText.getText(), typeCreateText.getText(), iconCreateText.getText(),
                imageCreateText.getText(), connectivityCreateText.getText(), createError)
                && !validateRobotData.validateNameExists(nameCreateText.getText())
                && validateRobotData.validateNumberRobots()) {
            String path = "/home/adrianadam/Desktop/FILS/Anul 3/Sem 2/MTDL/MTDLProject/src/sample/Resources/" + imageCreateChoice.getValue();
            databaseManager.insertNewRobot(nameCreateText.getText(), "idle", 0, 0, typeCreateText.getText(),
                    iconCreateText.getText(), path, connectivityCreateText.getText());

            createSuccess.setText(createSuccess.getText() + "\nYou have successfully created a new robot");
        }
    }

    public void deleteRobot(ActionEvent actionEvent) throws SQLException {
        if (validateRobotData.validateName(nameDeleteText.getText())
                && validateRobotData.validateNameExists(nameDeleteText.getText())) {
            databaseManager.deleteRobot(nameDeleteText.getText());

            deleteSuccess.setText(deleteSuccess.getText() + "\nSuccessfully deleted the robot");
        } else {
            deleteError.setText(deleteError.getText() + "\nRobot doesn't exist");
        }
    }

    public void getImageFiles(ActionEvent actionEvent) {
        imageCreateChoice.setValue("Image");
        imageCreateChoice.getItems().addAll("blue.jpg", "blue-light.png", "brown.jpg", "green.jpg", "grey.png", "orange.png",
                "purple.jpg", "red.jpg", "white.png", "yellow.jpg");

        imageEditChoice.setValue("Image");
        imageEditChoice.getItems().addAll("blue.jpg", "blue-light.png", "brown.jpg", "green.jpg", "grey.png", "orange.png",
                "purple.jpg", "red.jpg", "white.png", "yellow.jpg");
    }
}
