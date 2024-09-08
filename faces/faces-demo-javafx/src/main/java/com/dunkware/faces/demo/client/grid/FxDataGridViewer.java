package com.dunkware.faces.demo.client.grid;

import java.util.List;

import com.dunkware.faces.widgets.model.grid.DataGridColumn;
import com.dunkware.faces.widgets.model.grid.DataGridModel;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxDataGridViewer extends Application {

    private TableView<ObservableList<String>> tableView;
    private DataGridModel dataGridModel;

    // No-arg constructor required by JavaFX
    public FxDataGridViewer() {
    }

    public FxDataGridViewer(DataGridModel model) {
        this.dataGridModel = model;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize the DataGridModel (you can customize this as needed)
        this.dataGridModel = createSampleDataGridModel();

        BorderPane root = new BorderPane();

        // Initialize the table
        tableView = new TableView<>();
        configureTable(dataGridModel);

        // Add toolbar for managing visible/hidden columns
        ToolBar toolBar = createToolBar();

        root.setTop(toolBar);
        root.setCenter(tableView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void configureTable(DataGridModel dataGridModel) {
        // Add columns based on DataGridModel
        for (DataGridColumn column : dataGridModel.getColumns()) {
            TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(column.getHeader());
            int colIndex = dataGridModel.getColumns().indexOf(column);
            tableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));

            // Enable sorting for the column
            tableColumn.setSortable(true);
            tableView.getColumns().add(tableColumn);
        }
    }

    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button showHideColumnsButton = new Button("Manage Columns");
        showHideColumnsButton.setOnAction(event -> showManageColumnsDialog());

        toolBar.getItems().add(showHideColumnsButton);
        return toolBar;
    }

    private void showManageColumnsDialog() {
        Dialog<List<DataGridColumn>> dialog = new Dialog<>();
        dialog.setTitle("Manage Columns");

        ListView<DataGridColumn> visibleColumnsList = new ListView<>();
        visibleColumnsList.getItems().addAll(dataGridModel.getColumns());

        ListView<DataGridColumn> hiddenColumnsList = new ListView<>();

        Button addButton = new Button("Hide");
        addButton.setOnAction(e -> {
            DataGridColumn selected = visibleColumnsList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                visibleColumnsList.getItems().remove(selected);
                hiddenColumnsList.getItems().add(selected);
                tableView.getColumns().removeIf(col -> col.getText().equals(selected.getHeader()));
            }
        });

        Button removeButton = new Button("Show");
        removeButton.setOnAction(e -> {
            DataGridColumn selected = hiddenColumnsList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                hiddenColumnsList.getItems().remove(selected);
                visibleColumnsList.getItems().add(selected);
                TableColumn<ObservableList<String>, String> newColumn = new TableColumn<>(selected.getHeader());
                tableView.getColumns().add(newColumn);
            }
        });

        VBox content = new VBox(10, new HBox(10, visibleColumnsList, hiddenColumnsList), new HBox(10, addButton, removeButton));

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    // Sample DataGridModel creation for demo
    private DataGridModel createSampleDataGridModel() {
        return DataGridModel.builder()
                .entity("Practice")
                .addColumn(new DataGridColumn("id", "ID", "NUMBER", "Integer"))
                .addColumn(new DataGridColumn("value", "Value", "DECIMAL", "Double"))
                .addColumn(new DataGridColumn("timestamp", "Timestamp", "DATETIME", "String"))
                .build();
    }
}