package com.dunkware.xstream.model.script.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;
import com.dunkware.xstream.model.util.XScriptModelFormatter;

public class XScriptModelCopier {

    public static XScriptModel copy(XScriptModel original) {
        List<XScriptModelVariable> copiedVariables = original.getVariables().stream()
                .map(var -> new XScriptModelVariable(var.getVersion(), var.getId(), var.getName(), var.getLabel(), var.getGroup(), var.getFormat()))
                .collect(Collectors.toList());

        List<XScriptModelSignal> copiedSignals = original.getSignals().stream()
                .map(sig -> new XScriptModelSignal(sig.getId(), sig.getName(), sig.getLabel(), sig.getGroup(), sig.getVersion()))
                .collect(Collectors.toList());

        List<XScriptModelBot> copiedBots = original.getBots().stream()
                .map(bot -> new XScriptModelBot(bot.getName(), bot.getGroup(), bot.getVersion()))
                .collect(Collectors.toList());

        return new XScriptModel(original.getName(), original.getVersion(), copiedVariables, copiedSignals, copiedBots);
    }

    public static void main(String[] args) {
        // Example usage
        XScriptModel original = new XScriptModel("ExampleScript", "1.0.0", 
                List.of(new XScriptModelVariable("1.0.0", 1, "Variable1", "Label1", "Group1", "Format1")),
                List.of(new XScriptModelSignal(1, "Signal1", "Label1", "Group1", "1.0.0")),
                List.of(new XScriptModelBot("Bot1", "Group1", "1.0.0")));

        XScriptModel copied = copy(original);

        System.out.println("Original Model:");
        System.out.println(XScriptModelFormatter.format(original));

        System.out.println("Copied Model:");
        System.out.println(XScriptModelFormatter.format(copied));
    }
}
