package com.dunkware.street.smart.bot;

import com.dunkware.street.smart.bot.dialog.bottree.BotFolder;
import com.dunkware.street.smart.bot.dialog.bottree.BotNode;
import com.dunkware.street.smart.bot.dialog.bottree.BotTree;

public class TradeBots {

    private static TradeBots instance;
    private BotTree botTree;

    // Private constructor to prevent instantiation
    private TradeBots() {
        // Initialize the bot tree with some sample data
        botTree = new BotTree();
        BotFolder folder = new BotFolder("Folder 1");
        folder.addBot(new BotNode("Bot 1", "Description for Bot 1"));
        folder.addBot(new BotNode("Bot 2", "Description for Bot 2"));
        botTree.getRoot().addBot(folder);
    }

    // Singleton method to get the instance
    public static TradeBots getInstance() {
        if (instance == null) {
            instance = new TradeBots();
        }
        return instance;
    }

    // Method to get the bot tree
    public BotTree getBotTree() {
        return botTree;
    }
}