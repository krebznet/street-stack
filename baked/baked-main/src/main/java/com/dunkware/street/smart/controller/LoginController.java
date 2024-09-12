package com.dunkware.street.smart.controller;



import com.dunkware.street.smart.MainApp;
import com.dunkware.street.smart.ui.common.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass().getName());

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox<?> txtEnvironment;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    private MainApp app;

    public void setMainApp(MainApp app) {
        this.app = app;
    }

    @FXML
    void handleLogin(MouseEvent event) {
    logger.info("handle login");
    }

    @FXML
    void onExit(MouseEvent event) {
    logger.info("handle exit");
    }

    @FXML
    void onLogin(ActionEvent event) {
logger.info("onlogin");
app.showMain();;
    }

    @FXML
    void onSetPassword(ActionEvent event) {

    }

    @FXML
    void onSetUsername(ActionEvent event) {

    }

    @FXML
    void setEnvironment(InputMethodEvent event) {

    }
}
