package com.dunkware.street.smart.bot.dialog.bottree;

public class BotTree {

    private BotFolder root;

    public BotTree() {
        root = new BotFolder("Root");
    }

    public BotFolder getRoot() {
        return root;
    }

    public void addBotToFolder(BotFolder folder, BotNode bot) {
        folder.addBot(bot);
    }
}
