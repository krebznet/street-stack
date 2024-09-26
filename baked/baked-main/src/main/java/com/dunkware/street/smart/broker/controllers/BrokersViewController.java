package com.dunkware.street.smart.broker.controllers;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BrokersViewController {

    @FXML
    private TableView<Broker> brokerTable;
    @FXML
    private TableColumn<Broker, String> identifierColumn;
    @FXML
    private TableColumn<Broker, String> statusColumn;
    @FXML
    private TableColumn<Broker, Duration> durationColumn;
    @FXML
    private TableColumn<Broker, String> brokerTypeColumn;
    @FXML
    private TableColumn<Broker, Integer> accountCountColumn;

    private List<Broker> brokers;


    public void initialize() {
        // Initialize columns
        identifierColumn.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("statusDuration"));
        brokerTypeColumn.setCellValueFactory(new PropertyValueFactory<>("brokerType"));
        accountCountColumn.setCellValueFactory(new PropertyValueFactory<>("accountCount"));

        // Apply custom formatting for status column
        statusColumn.setCellFactory(getStatusCellFactory());

        // Apply custom formatting for duration column
        durationColumn.setCellFactory(getDurationCellFactory());

        // Load data
        loadBrokers();
    }

    private void loadBrokers() {
        brokers = new ArrayList<Broker>();
        // Load brokers into the table (replace this with actual broker list)
        brokerTable.setItems(FXCollections.observableArrayList(brokers));
    }

    private Callback<TableColumn<Broker, String>, TableCell<Broker, String>> getStatusCellFactory() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(status);
                    if (status.equals("Exception") || status.equals("Pending") || status.equals("ConnectLoop")) {
                        setStyle("-fx-background-color: red;");
                    } else if (!status.equals("Running")) {
                        setStyle("-fx-background-color: orange;");
                    } else {
                        setStyle("");  // Default style for Running
                    }
                }
            }
        };
    }

    private Callback<TableColumn<Broker, Duration>, TableCell<Broker, Duration>> getDurationCellFactory() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(Duration duration, boolean empty) {
                super.updateItem(duration, empty);
                if (empty || duration == null) {
                    setText(null);
                } else {
                    long days = duration.toDays();
                    long hours = duration.toHoursPart();
                    long minutes = duration.toMinutesPart();
                    long seconds = duration.toSecondsPart();
                    setText(String.format("%02d-%02d-%02d-%02d", days, hours, minutes, seconds));
                }
            }
        };
    }

    // Context menu logic
    @FXML
    private void handleRightClick(MouseEvent event) {
        if (event.isSecondaryButtonDown()) {
            Broker selectedBroker = brokerTable.getSelectionModel().getSelectedItem();
            if (selectedBroker != null) {
                ContextMenu contextMenu = new ContextMenu();

                // Connect menu item
                if (selectedBroker.getStatus().equals("DISCONNECTED")) {
                    MenuItem connectItem = new MenuItem("Connect");
                    connectItem.setOnAction(e -> handleConnectBroker(selectedBroker));
                    contextMenu.getItems().add(connectItem);
                }

                // Delete menu item
                MenuItem deleteItem = new MenuItem("Delete");
                deleteItem.setOnAction(e -> handleDeleteBroker(selectedBroker));
                contextMenu.getItems().add(deleteItem);

                contextMenu.show(brokerTable, event.getScreenX(), event.getScreenY());
            }
        }
    }

    public void handleAddBroker() {
        // Add broker logic
    }

    private void handleConnectBroker(Broker broker) {
        // Logic to connect broker
    }

    private void handleDeleteBroker(Broker broker) {
        // Logic to delete broker
    }
}