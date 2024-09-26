package com.dunkware.street.smart.bot.dialog.bottree;

public class BotNode {
    private String name;
    private String description;

    public BotNode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}