package com.dunkware.street.smart.bot.dialog.bottree;

import java.util.ArrayList;
import java.util.List;

public class BotFolder extends BotNode {
    private List<BotNode> children;

    public BotFolder(String name) {
        super(name, "");
        this.children = new ArrayList<>();
    }

    public void addBot(BotNode bot) {
        children.add(bot);
    }

    public List<BotNode> getChildren() {
        return children;
    }
}