package com.dunkware.street.smart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ShellController


{

    @FXML
    public void handleQuit(ActionEvent actionEvent) {
        System.exit(-1);
    }

    @FXML
    public void menuAbout(ActionEvent actionEvent) {
        System.out.println("About");
    }
}
