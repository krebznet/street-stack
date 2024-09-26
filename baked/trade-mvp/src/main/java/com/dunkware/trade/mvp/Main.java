package com.dunkware.trade.mvp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a simple label to display "Hello World"
        Label helloLabel = new Label("Hello, World!");

        // Set up the root pane and add the label
        StackPane root = new StackPane();
        root.getChildren().add(helloLabel);

        // Create a scene with the root pane, specifying its size
        Scene scene = new Scene(root, 400, 200);

        // Set up the stage (window) with a title and the scene
        primaryStage.setTitle("Hello World JavaFX");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}