package com.dunkware.street.smart.bot;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BotManagerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Trade Sauce");

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu botMenu = new Menu("Bot");
        MenuItem runBotMenuItem = new MenuItem("Run Bot");
        botMenu.getItems().add(runBotMenuItem);
        menuBar.getMenus().add(botMenu);

        // Tab Pane for Bots
        TabPane botTabPane = new TabPane();

        // Example Bot Tab
        Tab botTab = new Tab("Bot 1");
        botTab.setClosable(true);

        // Tab Pane for Views (Tabs at the bottom)
        TabPane viewTabPane = new TabPane();
        viewTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); // Prevent closing view tabs
        viewTabPane.setSide(Side.BOTTOM); // Position tabs at the bottom

        // Create individual view tabs
        Tab dashboardTab = new Tab("Dashboard", new Label("Dashboard Content"));
        Tab tradesTab = new Tab("Trades", new Label("Trades Content"));
        Tab ordersTab = new Tab("Orders", new Label("Orders Content"));
        Tab eventLogTab = new Tab("Event Log", new Label("Event Log Content"));

        // Add all tabs to the viewTabPane
        viewTabPane.getTabs().addAll(dashboardTab, tradesTab, ordersTab, eventLogTab);

        // Layout for the Bot Tab (Now with viewTabPane at the center)
        BorderPane botLayout = new BorderPane();
        botLayout.setCenter(viewTabPane);

        botTab.setContent(botLayout);
        botTabPane.getTabs().add(botTab);

        // Root Layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(botTabPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}