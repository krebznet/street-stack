package com.dunkware.faces.demo.client.guotes;
import java.util.List;

import com.dunkware.trade.feed.api.model.snapshot.EquitySnapshot;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EquitySnapshotApp extends Application {

    @Override
    public void start(Stage stage) {
        TableView<EquitySnapshot> tableView = new TableView<>();
        ObservableList<EquitySnapshot> snapshotData = FXCollections.observableArrayList();

        // Define the columns for the table
        TableColumn<EquitySnapshot, String> symbolColumn = new TableColumn<>("Symbol");
        symbolColumn.setCellValueFactory(new PropertyValueFactory<>("symbol"));

        TableColumn<EquitySnapshot, Double> lastPriceColumn = new TableColumn<>("Last Price");
        lastPriceColumn.setCellValueFactory(new PropertyValueFactory<>("lastPrice"));

        // Apply a custom cell factory to the lastPriceColumn to change text color based on value
        lastPriceColumn.setCellFactory(new Callback<TableColumn<EquitySnapshot, Double>, TableCell<EquitySnapshot, Double>>() {
            @Override
            public TableCell<EquitySnapshot, Double> call(TableColumn<EquitySnapshot, Double> column) {
                return new TableCell<EquitySnapshot, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(String.format("%.2f", item)); // Format the price to 2 decimal places

                            // Set text color based on value
                            if (item > 0) {
                                setStyle("-fx-text-fill: green;");  // Green for positive values
                            } else if (item < 0) {
                                setStyle("-fx-text-fill: red;");    // Red for negative values
                            } else {
                                setStyle("-fx-text-fill: black;");  // Black for zero values
                            }
                        }
                    }
                };
            }
        });

        TableColumn<EquitySnapshot, Integer> volumeColumn = new TableColumn<>("Volume");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));

        // Add more columns as needed for other fields

        tableView.getColumns().addAll(symbolColumn, lastPriceColumn, volumeColumn);

        // Create the VBox layout and add the table to it
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.setTitle("Equity Snapshot Table");
        stage.show();

        // Fetch the equity snapshots and populate the TableView
        fetchSnapshotsAndPopulateTable(tableView);
    }

    private void fetchSnapshotsAndPopulateTable(TableView<EquitySnapshot> tableView) {
        try {
        	
            QuoteFetcher fetcher = new QuoteFetcher();
            List<EquitySnapshot> snapshots = fetcher.fetchSnapshots();

            // Convert the list of snapshots to an observable list and set it in the TableView
            ObservableList<EquitySnapshot> snapshotData = FXCollections.observableArrayList(snapshots);
            tableView.setItems(snapshotData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}