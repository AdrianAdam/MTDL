/*
 * Developed by Adrian Adam on 5/19/19 6:40 PM.
 * Last modified 5/8/19 10:11 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Layout/loginLayout.fxml"));
        primaryStage.setTitle("Robot World");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
