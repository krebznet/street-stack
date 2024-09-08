package com.dunkware.faces.demo.client.grid;

import org.springframework.web.reactive.function.client.WebClient;

import com.dunkware.faces.widgets.model.grid.DataGridColumn;
import com.dunkware.faces.widgets.model.grid.DataGridModel;
import com.dunkware.utils.core.format.FormatType;
import com.dunkware.utils.core.format.Formatter;
import com.faces.widget.proto.grid.Datagrid;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reactor.core.publisher.Flux;

public class DataGridDemoApp extends Application {

    private static final String API_URL = "http://localhost:9090/api/stream/datagrid";
    private TableView<ObservableList<String>> tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create DataGridModel (this will usually come from your streaming data)
        DataGridModel dataGridModel = createSampleDataGridModel();

        // Initialize the JavaFX TableView based on DataGridModel
        tableView = new TableView<>();
        configureTable(dataGridModel);

        // Set up the layout
        VBox root = new VBox();
        root.getChildren().add(tableView);
        root.setFillWidth(true);  // Ensure the table fills the width

        // Create a scene and add it to the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Dunkware Fusion");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start pulling data from the backend
        startStreamingDataFromServer();
    }

    private void configureTable(DataGridModel dataGridModel) {
        // Create table columns based on DataGridModel columns
        for (DataGridColumn column : dataGridModel.getColumns()) {
            TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(column.getHeader());

            // Bind each column to the appropriate index of ObservableList<String>
            int colIndex = dataGridModel.getColumns().indexOf(column);
            tableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));

            // Enable sorting by column
            tableColumn.setSortable(true);

            // Add the column to the table
            tableView.getColumns().add(tableColumn);
        }

        // Enable table auto-sorting
        tableView.setSortPolicy(tv -> {
            tv.getColumns().forEach(column -> column.setSortable(true));
            return true;
        });
    }

    private DataGridModel createSampleDataGridModel() {
        // Create a DataGridModel for the entity "Practice"
        return DataGridModel.builder()
                .entity("Practice")
                .addColumn(new DataGridColumn("id", "ID", "NUMBER", "Integer"))
                .addColumn(new DataGridColumn("value", "Value", "DECIMAL", "Double"))
                .addColumn(new DataGridColumn("timestamp", "Timestamp", "DATETIME", "String"))
                .build();
    }

    private void startStreamingDataFromServer() {
        // Use WebClient to pull data from the stream
        WebClient webClient = WebClient.create();

        Flux<byte[]> responseFlux = webClient.get()
                .uri(API_URL)
                .retrieve()
                .bodyToFlux(byte[].class);

        responseFlux.subscribe(
                this::handleProtoBytes,    // OnNext: handle each received data
                error -> System.err.println("Error: " + error.getMessage()),  // OnError: handle errors
                () -> System.out.println("Stream completed")                  // OnComplete: handle completion
        );
    }

    // Handle the Protobuf data received from the stream
    private void handleProtoBytes(byte[] protoBytes) {
        try {
            // Parse the byte[] into DataGridUpdateProtoList
            Datagrid.DataGridUpdateProtoList protoList = Datagrid.DataGridUpdateProtoList.parseFrom(protoBytes);

            for (Datagrid.DataGridUpdateProto proto : protoList.getUpdatesList()) {
                ObservableList<String> rowData = FXCollections.observableArrayList(
                        Formatter.format(proto.getId(), FormatType.NUMBER),  // ID
                        Formatter.format(proto.getJson(), FormatType.TEXT),   // Value
                        Formatter.format(System.currentTimeMillis(), FormatType.NUMBER)  // Timestamp
                );

                // Update the table on the JavaFX Application thread
                Platform.runLater(() -> {
                    // Ensure the table's item list is a modifiable ObservableList
                    ObservableList<ObservableList<String>> items = tableView.getItems();
                    if (items == null) {
                        items = FXCollections.observableArrayList();
                        tableView.setItems(items);
                    }
                    items.add(rowData);
                });
            }

        } catch (Exception e) {
            System.err.println("Failed to parse Protobuf message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}