package com.dunkware.faces.demo.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class ConsoleStreamApp extends Application {

    private static final int MAX_BUFFER_SIZE = 5000; // Max characters allowed in the console
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        // Create the TextArea to simulate the console
        TextArea console = new TextArea();
        console.setEditable(false);
        console.setWrapText(true);
        console.setStyle("-fx-control-inner-background: black; -fx-text-fill: white;");
        console.setFont(Font.font("Monospaced", 14));

        // Make the TextArea fill the entire VBox (window space)
        VBox.setVgrow(console, Priority.ALWAYS);

        // Set up the scene
        VBox root = new VBox(console);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Data Stream Console");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Timeline for generating random text at regular intervals
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), e -> {
            String randomText = generateRandomText(80); // Generate 80-character random text
            appendTextWithLimit(console, randomText + "\n", MAX_BUFFER_SIZE);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Function to generate random text
    private String generateRandomText(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Append text to the console, limiting the buffer size
    private void appendTextWithLimit(TextArea console, String text, int maxSize) {
        console.appendText(text);
        if (console.getText().length() > maxSize) {
            console.deleteText(0, console.getText().length() - maxSize);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}