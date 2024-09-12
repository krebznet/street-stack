package com.dunkware.faces.demo.client.grid;



import java.awt.Desktop;
import java.net.URI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DunkwareFusionApp extends Application {

    private Button tradeBotsButton;
    private Button stockStreamButton;

    public static class LoginBean {
        private String environment;
        private String username;
        private String password;

        // Getters and setters
        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Override
    public void start(Stage primaryStage) {
    	   // Create the main layout
        BorderPane root = new BorderPane();

        // Load the background image
        Image backgroundImage = new Image("file:src/main/java/login-splash-1.png");  // adjust path as needed
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
        );
        root.setBackground(new Background(bgImage));

        // Create the title text
        Text title = new Text("MVP 1.0.2");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Arial", 40));

        // Create the hyperlink
        Hyperlink dunkwareLink = new Hyperlink("Dunkware Technology");
        dunkwareLink.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        // Set the action for the hyperlink click
        dunkwareLink.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://dunkware.com"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Create a VBox for the title and hyperlink
        VBox topBox = new VBox(5, title, dunkwareLink);
        topBox.setAlignment(Pos.TOP_LEFT);
        BorderPane.setMargin(topBox, new Insets(20, 20, 0, 20));
        root.setTop(topBox);

        // Create the buttons for "Trade Bots" and "Stock Stream"
        tradeBotsButton = new Button("Trade Bots");
        tradeBotsButton.setMinWidth(150);
        tradeBotsButton.setDisable(true); // Disabled initially

        stockStreamButton = new Button("Stock Stream");
        stockStreamButton.setMinWidth(150);
        stockStreamButton.setDisable(true); // Disabled initially

        Button loginButton = new Button("Login");
        loginButton.setMinWidth(150);

        // Create a VBox to hold the buttons
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(tradeBotsButton, stockStreamButton, loginButton);

        // Set the VBox in the center of the layout
        root.setCenter(buttonBox);

        // Create the scene
        Scene scene = new Scene(root, 600, 400);

        // Configure the stage
        primaryStage.setTitle("Dunkware MVP");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set button action for login
        loginButton.setOnAction(e -> showLoginDialog(primaryStage));

        // Add action for Trade Bots button to show feature description
        tradeBotsButton.setOnAction(e -> showFeatureDescription("Trade Bots", getTradeBotsDescription()));
    }

    private void showLoginDialog(Stage owner) {
        // Create a new dialog for login
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("Login");

        // Create the layout for the login form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // Set background color
        grid.setStyle("-fx-background-color: darkblue;");

        // Create the form fields
        ComboBox<String> environmentCombo = new ComboBox<>();
        environmentCombo.getItems().addAll("Development", "Testing");
        environmentCombo.setValue("Development");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Label errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);

        // Add form fields to the grid
        grid.add(new Label("Environment:"), 0, 0);
        grid.add(environmentCombo, 1, 0);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(errorMessage, 0, 3, 2, 1);

        // Create LoginBean
        LoginBean loginBean = new LoginBean();

        // Create the login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            loginBean.setEnvironment(environmentCombo.getValue());
            loginBean.setUsername(usernameField.getText());
            loginBean.setPassword(passwordField.getText());

            // Authenticate the user
            if (authenticate(loginBean)) {
                // On successful login, close the dialog and enable buttons
                dialog.close();
                tradeBotsButton.setDisable(false);
                stockStreamButton.setDisable(false);
            } else {
                // Show error message
                errorMessage.setText("Invalid login. Please try again.");
            }
        });

        // Add login button to the grid
        HBox loginButtonBox = new HBox();
        loginButtonBox.setAlignment(Pos.CENTER);
        loginButtonBox.getChildren().add(loginButton);
        grid.add(loginButtonBox, 1, 4);

        // Set the scene and show the dialog
        Scene dialogScene = new Scene(grid, 400, 300);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private boolean authenticate(LoginBean loginBean) {
        // Placeholder authentication logic
        return "user".equals(loginBean.getUsername()) && "password".equals(loginBean.getPassword());
    }

    private void showFeatureDescription(String featureName, String description) {
    	Dialog<Void> tradeBotDialog = new Dialog<>();
    	tradeBotDialog.setTitle("Feature Description - Trade Bots");

    	VBox dialogContent = new VBox();
    	dialogContent.setSpacing(10);

    	// Add a scrollable, read-only text area for the description
    	TextArea descriptionArea = new TextArea();
    	descriptionArea.setEditable(false);
    	descriptionArea.setWrapText(true);

    	// Set the text content
    	descriptionArea.setText(
    	    "Trade Bots leverage Dunkware’s advanced Time Stream engine, allowing fully automated, scriptable trading strategies through Dunkware Script. " +
    	    "A trade bot can allocate capital, integrate seamlessly with brokerage accounts via APIs, and execute dynamic trading decisions based on a flexible set of trade plays. " +
    	    "Each play is defined by extendable entry and exit signals, as well as trade executors that determine how positions are managed when signals are triggered.\n\n" +
    	    "By utilizing Dunkware’s multi-day query engine, MVP Bots are empowered to analyze historical and real-time data, identifying key patterns and breakouts over multiple time frames. " +
    	    "This enables sophisticated strategies, such as multi-day breakout trading with precise exit logic combining stop losses, trailing stops, and other signal-based rules. " +
    	    "The bots can incorporate additional criteria, such as unrealized gains/losses, specific time-based triggers, and custom timers, all while dynamically reacting to real-time signals streaming from Dunkware’s time series data streams."
    	);

    	// Make the text area scrollable
    	descriptionArea.setPrefHeight(200);
    	ScrollPane scrollPane = new ScrollPane(descriptionArea);
    	scrollPane.setFitToWidth(true);
    	scrollPane.setFitToHeight(true);

    	// Add the scroll pane to the dialog content
    	dialogContent.getChildren().add(scrollPane);

    	tradeBotDialog.getDialogPane().setContent(dialogContent);
    	tradeBotDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

    	// Show the dialog when needed
    	tradeBotDialog.showAndWait();
    }

    private String getTradeBotsDescription() {
        return "Trade Bots show integration into Dunkware Time Streams where Trade Bots can be scripted in Dunkware Script and work on the concept of a trade bot defining a fixed capital allocation amount, a selected brokerage account connected via an API and a set of trade plays that have extendable types of entry and exit signals and also define entry and exit executors that define how to enter and exit a trade once a signal is triggered. " +
               "Building on the base trade bot engine, MVP Bots have extended the engine with signal entry signals and bring the ability to programmatically trade on multi-day breakouts with exit logic that can combine stop losses or trailing stops with other types of signals like unrealized gain/loss percentage, a specific time of the day, a timer for testing, and also signals defined in the stream.";
    }

    public static void main(String[] args) {
        launch(args);
    }
}