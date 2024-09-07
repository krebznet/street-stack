package com.dunkware.street.smart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp2 extends Application {

    private StackPane view1; // View 1
    private StackPane view2; // View 2
    private BorderPane mainLayout; // Main layout

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout
        mainLayout = new BorderPane();

        // Create the toolbar with buttons
        ToolBar toolBar = new ToolBar();
        Button btnShowView1 = new Button("Show View 1");
        Button btnShowView2 = new Button("Show View 2");

        toolBar.getItems().addAll(btnShowView1, btnShowView2);
        mainLayout.setTop(toolBar);

        // Initialize views
        view1 = createView1();
        view2 = createView2();

        // Show the first view by default
        mainLayout.setCenter(view1);

        // Set button actions to switch views
        btnShowView1.setOnAction(e -> switchView(view1));
        btnShowView2.setOnAction(e -> switchView(view2));

        // Set up the scene
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Street Smart App");
        primaryStage.show();
    }

    private StackPane createView1() {
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("This is View 1"));
        return pane;
    }

    private StackPane createView2() {
        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("This is View 2"));
        return pane;
    }

    private void switchView(StackPane view) {
        mainLayout.setCenter(view); // Switch the center content to the selected view
    }

    public static void main(String[] args) {
        launch(args);
    }
}