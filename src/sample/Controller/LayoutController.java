package sample.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LayoutController {

    public LayoutController() {
    }

    public void openWindow(String layoutName, Stage stage) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("../Layout/" + layoutName + ".fxml"));
        Scene nextScene = new Scene(nextWindow);

        Stage window = stage;

        window.setScene(nextScene);
        window.show();
    }
}
