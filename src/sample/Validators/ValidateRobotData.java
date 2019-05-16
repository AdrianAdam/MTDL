package sample.Validators;

import javafx.scene.control.Label;
import sample.Manager.DatabaseManager;
import sample.Model.Robot;

import java.sql.SQLException;
import java.util.List;

public class ValidateRobotData {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();

    public ValidateRobotData() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public boolean validateNameExists(String name) {
        List<Robot> robots = databaseManager.selectAllRobots();

        for(int i = 0; i < robots.size(); i++) {
            if (robots.get(i).getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean validateName(String name) {
        if (name.equals("")) {
            return false;
        }

        return true;
    }

    public boolean validateNumberRobots() throws SQLException {
        if (databaseManager.getNumberRobots() < 10) {
            return true;
        }
        return false;
    }

    public boolean validateAllData(String name, String type, String icon, String image, String connectivity, Label error) {
        boolean ok = true;

        if (name.equals("")) {
            error.setText(error.getText() + "\nName can't be empty");
            ok = false;
        }
        if (type.equals("")) {
            error.setText(error.getText() + "\nType can't be empty");
            ok = false;
        }
        if (icon.equals("")) {
            error.setText(error.getText() + "\nIcon can't be empty");
            ok = false;
        }
        if (image.equals("")) {
            error.setText(error.getText() + "\nImage can't be empty");
            ok = false;
        }
        if (connectivity.equals("")) {
            error.setText(error.getText() + "\nConnectivity can't be empty");
            ok = false;
        }

        if (ok) {
            return true;
        } else {
            return false;
        }
    }
}
