package com.dunkware.faces.demo.client.grid;



import com.dunkware.utils.core.format.FormatType;
import com.dunkware.utils.core.format.Formatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FxVariableViewer extends Application {

    private Accordion accordion;
    private ObservableList<VariableGroup> variableGroups;

    private final List<String> groupNames = Arrays.asList("Input", "Moving Average", "Moving Trade Count", "Moving Volume Count", "Momentum");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create toolbar with actions
        ToolBar toolbar = createToolbar();
        root.setTop(toolbar);

        accordion = new Accordion();
        root.setCenter(accordion);

        variableGroups = FXCollections.observableArrayList();

        // Create a scene and set up the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Real-Time Variable Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start mock data stream
        subscribeToVariableStream(generateMockData());
    }

    private ToolBar createToolbar() {
        Button expandAllBtn = new Button("Expand All");
        expandAllBtn.setOnAction(e -> expandAllGroups());

        Button collapseAllBtn = new Button("Collapse All");
        collapseAllBtn.setOnAction(e -> collapseAllGroups());

        Button showNonEmptyBtn = new Button("Show Non-Empty");
        showNonEmptyBtn.setOnAction(e -> filterNonEmptyRows());

        HBox hbox = new HBox(10, expandAllBtn, collapseAllBtn, showNonEmptyBtn);
        return new ToolBar(hbox);
    }

    private void expandAllGroups() {
        accordion.getPanes().forEach(pane -> pane.setExpanded(true));
    }

    private void collapseAllGroups() {
        accordion.getPanes().forEach(pane -> pane.setExpanded(false));
    }

    private void filterNonEmptyRows() {
        for (VariableGroup group : variableGroups) {
            group.filterNonEmptyRows();
        }
    }

    public void subscribeToVariableStream(Flux<VariableData> variableStream) {
        variableStream.subscribe(variableData -> Platform.runLater(() -> updateVariable(variableData)));
    }

    private void updateVariable(VariableData variableData) {
        // Find or create the group
        VariableGroup group = findOrCreateGroup(variableData.getGroupName());

        // Update or add variable in the group
        group.updateVariable(variableData);
    }

    private VariableGroup findOrCreateGroup(String groupName) {
        return variableGroups.stream()
                .filter(group -> group.getGroupName().equals(groupName))
                .findFirst()
                .orElseGet(() -> createNewGroup(groupName));
    }

    private VariableGroup createNewGroup(String groupName) {
        VariableGroup newGroup = new VariableGroup(groupName);
        variableGroups.add(newGroup);
        accordion.getPanes().add(newGroup.getTitledPane());
        return newGroup;
    }

    // Inner class for grouping variables
    class VariableGroup {
        private String groupName;
        private TableView<VariableData> table;
        private TitledPane titledPane;

        public VariableGroup(String groupName) {
            this.groupName = groupName;
            this.table = new TableView<>();

            // Define columns for variable name and value
            TableColumn<VariableData, String> variableNameCol = new TableColumn<>("Variable Name");
            variableNameCol.setCellValueFactory(param -> param.getValue().variableNameProperty());

            TableColumn<VariableData, String> variableValueCol = new TableColumn<>("Value");
            variableValueCol.setCellValueFactory(param -> param.getValue().formattedValueProperty());

            table.getColumns().addAll(variableNameCol, variableValueCol);
            this.titledPane = new TitledPane(groupName, table);
        }

        public String getGroupName() {
            return groupName;
        }

        public TitledPane getTitledPane() {
            return titledPane;
        }

        public void updateVariable(VariableData variableData) {
            table.getItems().removeIf(item -> item.getVariableName().equals(variableData.getVariableName()));
            table.getItems().add(variableData);
        }

        public void filterNonEmptyRows() {
            ObservableList<VariableData> nonEmptyRows = FXCollections.observableArrayList();

            for (VariableData variable : table.getItems()) {
                if (!variable.getFormattedValue().isEmpty()) {
                    nonEmptyRows.add(variable);
                }
            }

            table.setItems(nonEmptyRows);
        }
    }

    // Mock data generation for testing
    private Flux<VariableData> generateMockData() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> {
                    int groupIndex = ThreadLocalRandom.current().nextInt(0, groupNames.size());
                    String groupName = groupNames.get(groupIndex);

                    // Generate mock variable names based on group name
                    String variableName = groupName.replace(" ", "") + (ThreadLocalRandom.current().nextInt(1, 11));
                    double randomValue = ThreadLocalRandom.current().nextDouble(1.0, 100.0);
                    FormatType formatType = FormatType.DECIMAL;

                    return new VariableData(groupName, variableName, randomValue, formatType);
                });
    }
}

// Model class to represent the incoming data
class VariableData {
    private String groupName;
    private String variableName;
    private Object value;
    private FormatType formatType;

    public VariableData(String groupName, String variableName, Object value, FormatType formatType) {
        this.groupName = groupName;
        this.variableName = variableName;
        this.value = value;
        this.formatType = formatType;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getVariableName() {
        return variableName;
    }

    public Object getValue() {
        return value;
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public javafx.beans.property.SimpleStringProperty variableNameProperty() {
        return new javafx.beans.property.SimpleStringProperty(variableName);
    }

    public javafx.beans.property.SimpleStringProperty formattedValueProperty() {
        return new javafx.beans.property.SimpleStringProperty(Formatter.format(value, formatType));
    }

    public String getFormattedValue() {
        return Formatter.format(value, formatType);
    }
}