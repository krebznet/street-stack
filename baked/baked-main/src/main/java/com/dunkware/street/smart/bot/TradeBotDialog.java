package com.dunkware.street.smart.bot;

import com.dunkware.street.smart.bot.dialog.BotSelectionDialog;
import com.dunkware.street.smart.bot.dialog.bottree.BotNode;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TradeBotDialog extends Application {

    private TextField selectedBotField;  // Field to show the selected bot

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Run Trade Bot");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Bot Name Field
        Label botNameLabel = new Label("Bot Name:");
        TextField botNameField = new TextField();
        grid.add(botNameLabel, 0, 0);
        grid.add(botNameField, 1, 0);

        // TWS Host Field
        Label twsHostLabel = new Label("TWS Host:");
        TextField twsHostField = new TextField();
        grid.add(twsHostLabel, 0, 1);
        grid.add(twsHostField, 1, 1);

        // TWS Port Field
        Label twsPortLabel = new Label("TWS Port:");
        TextField twsPortField = new TextField();
        grid.add(twsPortLabel, 0, 2);
        grid.add(twsPortField, 1, 2);

        // TWS Client ID Field
        Label twsClientLabel = new Label("TWS Client ID:");
        TextField twsClientField = new TextField();
        grid.add(twsClientLabel, 0, 3);
        grid.add(twsClientField, 1, 3);

        // Selected Bot Field with Select button (to pick from the tree)
        Label selectedBotLabel = new Label("Selected Bot:");
        selectedBotField = new TextField();
        selectedBotField.setEditable(false);  // Read-only field to display selected bot

        Button selectBotButton = new Button("...");  // Button to open bot selection dialog
        selectBotButton.setOnAction(event -> openBotSelectionDialog());

        // HBox to align the TextField and the Button in a row
        HBox botSelectionLayout = new HBox(5, selectedBotField, selectBotButton);
        grid.add(selectedBotLabel, 0, 4);
        grid.add(botSelectionLayout, 1, 4);

        // Create the Scene and show the Stage
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Opens the bot selection dialog and sets the selected bot in the text field
    private void openBotSelectionDialog() {
        BotSelectionDialog botSelectionDialog = new BotSelectionDialog();

        // When the user selects a bot, set the selected bot's name in the text field
        botSelectionDialog.setOnBotSelected(botNode -> {
            if (botNode != null) {
                selectedBotField.setText(botNode.getName());
            }
        });

        botSelectionDialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}