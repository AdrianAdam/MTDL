package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Manager.DatabaseManager;
import sample.Model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainController {
    public TextField usernameTextLogin;
    public TextField passwordTextLogin;
    public TextField usernameTextRegister;
    public TextField passwordRegister;
    public TextField emailRegister;
    private DatabaseManager databaseManager = DatabaseManager.getInstance();

    public MainController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void login(ActionEvent actionEvent) throws IOException {
        List<User> users;
        users = databaseManager.selectAllUsers();

        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(usernameTextLogin.getText()) && users.get(i).getPassword().equals(passwordTextLogin.getText())) {
                Parent centralWindow = FXMLLoader.load(getClass().getResource("Layout/centralLayout.fxml"));
                Scene centralScene = new Scene(centralWindow);

                Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

                window.setScene(centralScene);
                window.show();
            }
        }
    }

    public void register(ActionEvent actionEvent) throws SQLException, IOException {
        databaseManager.insertNewUser(usernameTextRegister.getText(), passwordRegister.getText(), emailRegister.getText());

        Parent centralWindow = FXMLLoader.load(getClass().getResource("Layout/centralLayout.fxml"));
        Scene centralScene = new Scene(centralWindow);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(centralScene);
        window.show();
    }
}
