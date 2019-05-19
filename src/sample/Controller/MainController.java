/*
 * Developed by Adrian Adam on 5/19/19 6:38 PM.
 * Last modified 5/16/19 2:22 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    public TextField usernameTextLogin;
    public TextField passwordTextLogin;
    public TextField usernameTextRegister;
    public TextField passwordRegister;
    public TextField emailRegister;
    public Label loginError;
    public Label registerError;
    public Label registerSuccess;
    private RegisterController registerController = new RegisterController();
    private LoginController loginController = new LoginController();

    public MainController() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void login(ActionEvent actionEvent) throws IOException {
        loginError.setText("");
        loginController.loginUser(usernameTextLogin.getText(), passwordTextLogin.getText(), actionEvent, loginError);
    }

    public void register(ActionEvent actionEvent) throws SQLException, IOException {
        registerError.setText("");
        registerController.registerUser(usernameTextRegister.getText(), passwordRegister.getText(), emailRegister.getText(), registerError, registerSuccess);
    }
}
