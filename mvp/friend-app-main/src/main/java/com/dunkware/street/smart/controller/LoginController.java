package com.dunkware.street.smart.controller;



import com.dunkware.street.smart.MainApp;
import com.dunkware.street.smart.ui.common.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass().getName());
    @FXML
    private TextField gateway;

    @FXML
    private Button login;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    private MainApp app;

    private Stage modalStage;

    public void setModalStage(Stage stage) {
        this.modalStage = stage;
    }


    public void setApplication(MainApp app) {
        this.app = app;
    }

    @FXML
    void onGateway(MouseEvent event) {
        logger.error("on gateway");
    }

    @FXML
    void onLogin(MouseEvent event) {
        logger.error("on login");
        //modalStage.close();

        app.showMain();

    }

    @FXML
    void onTxtGateway(KeyEvent event) {
        logger.error("on gateway");
    }

    @FXML
    void onTxtPassword(KeyEvent event) {
        logger.error("on text password");
    }

    @FXML
    void onTxtUsername(KeyEvent event) {
    logger.error("on username");


    }



}
