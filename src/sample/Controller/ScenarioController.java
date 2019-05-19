/*
 * Developed by Adrian Adam on 5/19/19 6:36 PM.
 * Last modified 5/19/19 6:25 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.FileManagement.FileManagement;
import sample.Manager.DatabaseManager;
import sample.Model.Robot;
import sample.Movement.Movement;
import sample.Movement.MovementFactory;
import sample.Validators.ValidateRoboMovement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScenarioController {
    public Label robot1;
    public Label robot2;
    public Label robot3;
    public Label robot4;
    public Label robot5;
    public Label robot6;
    public Label robot7;
    public Label robot8;
    public Label robot9;
    public Label robot10;
    public TextField moveValue;
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public ImageView image5;
    public ImageView image6;
    public ImageView image7;
    public ImageView image8;
    public ImageView image9;
    public ImageView image10;

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private LayoutController layoutController = new LayoutController();
    private ValidateRoboMovement roboMovementController = new ValidateRoboMovement();
    private FileManagement fileManagement = new FileManagement();

    private Robot currentRobot = null;
    private List<Robot> robots = null;
    private List<ImageView> imageViews = null;
    private List<Label> labels = null;

    public ScenarioController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, FileNotFoundException, UnsupportedEncodingException {
    }

    public void startMoving(MouseEvent mouseEvent) throws SQLException, MalformedURLException {
        if (mouseEvent.getSource().toString().contains("robot")) {
            String[] text = mouseEvent.getSource().toString().split("'");
            String labelRobot = text[1];

            currentRobot = databaseManager.selectOneRobot(labelRobot);
        } else if (currentRobot != null && mouseEvent.getSource().toString().contains("Rectangle")) {
            System.out.println(mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
            fileManagement.writeNewLine(currentRobot.getName() + "\n Old coordinates: " + currentRobot.getCoordX() + " " + currentRobot.getCoordY());

            currentRobot.setCoordX((int) mouseEvent.getSceneX());
            currentRobot.setCoordY((int) mouseEvent.getSceneY());
            databaseManager.updateRobot(currentRobot.getName(), currentRobot.getState(), currentRobot.getCoordX(), currentRobot.getCoordY()
                    ,currentRobot.getType(), currentRobot.getIcon(), currentRobot.getImage(), currentRobot.getConnectivity());

            fileManagement.writeNewLine(" New coordinates: " + currentRobot.getCoordX() + " " + currentRobot.getCoordY() + "\nAd");

            updateVisualPositionRobot();
        }
    }

    public void updateVisualPositionRobot() {
        for(int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getText().equals(currentRobot.getName())) {
                Image image = new Image(currentRobot.getImage());
                imageViews.get(i).setImage(image);
                imageViews.get(i).setX(currentRobot.getCoordX());
                imageViews.get(i).setY(currentRobot.getCoordY());
                imageViews.get(i).setFitHeight(50);
                imageViews.get(i).setFitWidth(50);
                imageViews.get(i).setPreserveRatio(true);
            }
        }
    }

    public void getRobots(ActionEvent actionEvent) {
        robots = databaseManager.selectAllRobots();
        labels = new ArrayList<>();
        labels.add(robot1);
        labels.add(robot2);
        labels.add(robot3);
        labels.add(robot4);
        labels.add(robot5);
        labels.add(robot6);
        labels.add(robot7);
        labels.add(robot8);
        labels.add(robot9);
        labels.add(robot10);

        imageViews = new ArrayList<>();
        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);
        imageViews.add(image6);
        imageViews.add(image7);
        imageViews.add(image8);
        imageViews.add(image9);
        imageViews.add(image10);

        for (int i = 0; i < robots.size(); i++) {
            labels.get(i).setText(robots.get(i).getName());
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        fileManagement.closeWriter();
        layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }

    public void moveUp(ActionEvent actionEvent) throws SQLException {
        if (currentRobot != null) {
            if (roboMovementController.checkMovement(currentRobot.getCoordX(), currentRobot.getCoordY(), Integer.parseInt(moveValue.getText()), "up")) {
                executeMovement("up");
            }
        }
    }

    public void moveLeft(ActionEvent actionEvent) throws SQLException {
        if (currentRobot != null) {
            if (roboMovementController.checkMovement(currentRobot.getCoordX(), currentRobot.getCoordY(), Integer.parseInt(moveValue.getText()), "left")) {
                executeMovement("left");
            }
        }
    }

    public void moveRight(ActionEvent actionEvent) throws SQLException {
        if (currentRobot != null) {
            if (roboMovementController.checkMovement(currentRobot.getCoordX(), currentRobot.getCoordY(), Integer.parseInt(moveValue.getText()), "right")) {
                executeMovement("right");
            }
        }
    }

    public void moveDown(ActionEvent actionEvent) throws SQLException {
        if (currentRobot != null) {
            if (roboMovementController.checkMovement(currentRobot.getCoordX(), currentRobot.getCoordY(), Integer.parseInt(moveValue.getText()), "down")) {
                executeMovement("down");
            }
        }
    }

    public void executeMovement(String direction) throws SQLException {
        MovementFactory movementFactory = new MovementFactory();
        Movement movement = movementFactory.getMovement(direction);
        movement.move(currentRobot, Integer.parseInt(moveValue.getText()));

        databaseManager.updateRobot(currentRobot.getName(), currentRobot.getState(), currentRobot.getCoordX(), currentRobot.getCoordY(), currentRobot.getType(),
                currentRobot.getIcon(), currentRobot.getImage(), currentRobot.getConnectivity());
        updateVisualPositionRobot();
    }
}
