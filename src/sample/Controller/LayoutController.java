/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/12/19 7:29 PM.
 * Copyright (c) 2019. All rights reserved.
 */

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
