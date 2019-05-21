/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/19/19 6:11 PM.
 * Copyright (c) 2019. All rights reserved.
 */

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
    public TextField connectivityCreateText;
    public Label editError;
    public Label createError;
    public Label deleteError;
    public TextField nameEditText;
    public TextField typeEditText;
    public TextField iconEditText;
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

    private boolean imageInstantiateCheck = false;

    public RobotManagementController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void saveEdit(ActionEvent actionEvent) throws SQLException {
        if (validateRobotData.validateNameExists(nameEditText.getText())
                && validateRobotData.validateAllData(nameEditText.getText(), typeEditText.getText(),
                iconEditText.getText(), imageEditChoice.getValue(), connectivityEditText.getText(), editError)) {
            String path = "sample/Resources/" + imageEditChoice.getValue();
            databaseManager.updateRobot(nameEditText.getText(), currentRobotToEdit.getState(), currentRobotToEdit.getCoordX(),
                    currentRobotToEdit.getCoordY(), typeEditText.getText(), iconEditText.getText(), path, connectivityEditText.getText());

            editSuccess.setText(editSuccess.getText() + "\nSuccessfully edited the robot");
        }
    }

    public void searchEdit(ActionEvent actionEvent) throws SQLException {
        if (validateRobotData.validateNameExists(nameEditText.getText()) && imageInstantiateCheck) {
            Robot robot = databaseManager.selectOneRobot(nameEditText.getText());

            String[] path = robot.getImage().split("/");

            typeEditText.setText(robot.getType());
            iconEditText.setText(robot.getIcon());
            imageEditChoice.setValue(path[path.length - 1]);
            connectivityEditText.setText(robot.getConnectivity());

            currentRobotToEdit = robot;

            editSuccess.setText(editSuccess.getText() + "\nRobot found");
        } else {
            editError.setText(editError.getText() + "\nRobot doesn't exist or you didn't instantiate the images");
        }
    }

    public void saveCreate(ActionEvent actionEvent) throws SQLException {
        if (imageInstantiateCheck) {
            if (validateRobotData.validateAllData(nameCreateText.getText(), typeCreateText.getText(), iconCreateText.getText(),
                    imageCreateChoice.getValue(), connectivityCreateText.getText(), createError)
                    && !validateRobotData.validateNameExists(nameCreateText.getText())
                    && validateRobotData.validateNumberRobots()) {
                String path = "sample/Resources/" + imageCreateChoice.getValue();
                databaseManager.insertNewRobot(nameCreateText.getText(), "idle", 0, 0, typeCreateText.getText(),
                        iconCreateText.getText(), path, connectivityCreateText.getText());

                createSuccess.setText(createSuccess.getText() + "\nYou have successfully created a new robot");
            }
        } else {
            createError.setText(createError.getText() + "\nYou didn't instantiate the images");
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

        imageInstantiateCheck = true;
    }
}
