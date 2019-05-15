package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.Robot;

import java.io.IOException;
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

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private LayoutController layoutController = new LayoutController();

    private Robot currentRobot = null;
    private List<Robot> robots = null;

    public ScenarioController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void startMoving(MouseEvent mouseEvent) throws SQLException {
        if (mouseEvent.getSource().toString().contains("robot")) {
            String[] text = mouseEvent.getSource().toString().split("'");
            String labelRobot = text[1];

            currentRobot = databaseManager.selectOneRobot(labelRobot);
            System.out.println(currentRobot.toString());
        } else {
            // i clicked on rectangle. i need to place the robot here. get coordinates with mouseEvent.getSceneX() and getSceneY
        }
    }

    public void getRobots(ActionEvent actionEvent) {
        robots = databaseManager.selectAllRobots();
        List<Label> labels = new ArrayList<>();
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

        for (int i = 0; i < robots.size(); i++) {
            labels.get(i).setText(robots.get(i).getName());
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        layoutController.openWindow("centralLayout", (Stage)((Node)actionEvent.getSource()).getScene().getWindow());
    }
}
