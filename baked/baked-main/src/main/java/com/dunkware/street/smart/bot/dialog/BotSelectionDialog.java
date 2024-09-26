package com.dunkware.street.smart.bot.dialog;

import com.dunkware.street.smart.bot.dialog.bottree.BotFolder;
import com.dunkware.street.smart.bot.dialog.bottree.BotNode;
import com.dunkware.street.smart.bot.dialog.bottree.BotTree;
import com.dunkware.street.smart.bot.TradeBots;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.function.Consumer;

public class BotSelectionDialog extends Stage {

    private final BotTree botTree;
    private BotNode selectedBot;
    private Consumer<BotNode> onBotSelected;  // Callback to notify the selection

    public BotSelectionDialog() {
        setTitle("Select Bot");

        botTree = TradeBots.getInstance().getBotTree();  // Get bot tree from singleton

        TreeView<BotNode> treeView = createTreeView();
        ScrollPane treeScrollPane = new ScrollPane(treeView);
        treeScrollPane.setFitToWidth(true);

        // Create the description area
        TextArea descriptionArea = new TextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setPrefHeight(150);  // Set height for approx 10 lines
        descriptionArea.setWrapText(true);

        // Create "Close" and "Select" buttons
        Button closeButton = new Button("Close");
        Button selectButton = new Button("Select");
        closeButton.setDisable(true);
        selectButton.setDisable(true);

        // Layout for buttons at the bottom
        HBox buttonLayout = new HBox(10, closeButton, selectButton);
        buttonLayout.setAlignment(Pos.CENTER_RIGHT);
        buttonLayout.setPadding(new Insets(10));

        VBox mainLayout = new VBox(5, treeScrollPane, descriptionArea, buttonLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setPrefWidth(400);
        mainLayout.setPrefHeight(500);  // Taller window

        // Enable "Close" and "Select" buttons when a bot is selected
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.getValue() instanceof BotNode) {
                selectedBot = newValue.getValue();
                descriptionArea.setText(selectedBot.getDescription());
                closeButton.setDisable(false);
                selectButton.setDisable(false);
            } else {
                descriptionArea.clear();
                closeButton.setDisable(true);
                selectButton.setDisable(true);
            }
        });

        // Close button action
        closeButton.setOnAction(event -> this.close());

        // Select button action - notify the listener with the selected bot
        selectButton.setOnAction(event -> {
            if (onBotSelected != null && selectedBot != null) {
                onBotSelected.accept(selectedBot);
            }
            this.close();
        });

        Scene scene = new Scene(mainLayout, 400, 500);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
    }

    private TreeView<BotNode> createTreeView() {
        TreeItem<BotNode> rootItem = new TreeItem<>(botTree.getRoot());
        rootItem.setExpanded(true);
        addChildrenToTreeItem(rootItem, botTree.getRoot());
        TreeView<BotNode> treeView = new TreeView<>(rootItem);

        treeView.setCellFactory(new Callback<TreeView<BotNode>, TreeCell<BotNode>>() {
            @Override
            public TreeCell<BotNode> call(TreeView<BotNode> param) {
                return new TreeCell<>() {
                    @Override
                    protected void updateItem(BotNode item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.getName());
                            if (!(item instanceof BotFolder)) {
                                Tooltip tooltip = new Tooltip(item.getDescription());
                                tooltip.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
                                setTooltip(tooltip);
                            } else {
                                setTooltip(null);
                            }
                        } else {
                            setText(null);
                            setTooltip(null);
                        }
                    }
                };
            }
        });

        return treeView;
    }

    private void addChildrenToTreeItem(TreeItem<BotNode> parentItem, BotFolder parentFolder) {
        for (BotNode botNode : parentFolder.getChildren()) {
            TreeItem<BotNode> childItem = new TreeItem<>(botNode);
            if (botNode instanceof BotFolder) {
                addChildrenToTreeItem(childItem, (BotFolder) botNode);
            }
            parentItem.getChildren().add(childItem);
        }
    }

    // Set the callback to handle bot selection
    public void setOnBotSelected(Consumer<BotNode> onBotSelected) {
        this.onBotSelected = onBotSelected;
    }
}