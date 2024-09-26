package com.dunkware.street.smart.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.Set;

public class ShellController {

    @FXML
    private TabPane tabPane; // The TabPane that holds all tabs, including Brokers

    @FXML
    private Tab tabBrokers; // The Brokers tab where the BrokersView will be loaded

    @FXML
    private AnchorPane brokersTabContent; // This is the placeholder AnchorPane for the Brokers view

    @FXML
    public void initialize() {
        try {
            // Load BrokersView FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/brokers.fxml"));
            Parent brokersView = loader.load();

            // Ensure that brokersView takes up the full space of brokersTabContent
            // Set anchors to 0.0 for top, bottom, left, and right
            AnchorPane.setTopAnchor(brokersView, 0.0);
            AnchorPane.setBottomAnchor(brokersView, 0.0);
            AnchorPane.setLeftAnchor(brokersView, 0.0);
            AnchorPane.setRightAnchor(brokersView, 0.0);

            // Set the loaded FXML content into the brokers tab content
            brokersTabContent.getChildren().setAll(brokersView);
        } catch (IOException e) {
            e.printStackTrace();  // Log any exceptions that occur
        }
    }

    @FXML
    public void handleQuit(ActionEvent actionEvent) {
        System.exit(-1);  // Exit the application
    }

    @FXML
    public void menuAbout(ActionEvent actionEvent) {
        System.out.println("About");
    }
}