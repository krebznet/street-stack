package com.dunkware.xstream.model.script.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;

public class XScriptModelCopier {

    public static XScriptDescriptor copy(XScriptDescriptor original) {
        List<XScriptVariableDescriptor> copiedVariables = original.getVariables().stream()
                .map(var -> new XScriptVariableDescriptor(var.getVersion(), var.getId(), var.getName(), var.getLabel(), var.getGroup(), var.getFormat()))
                .collect(Collectors.toList());

        List<XScriptSignalDescriptor> copiedSignals = original.getSignals().stream()
                .map(sig -> new XScriptSignalDescriptor(sig.getId(), sig.getName(), sig.getLabel(), sig.getGroup(), sig.getVersion()))
                .collect(Collectors.toList());

        List<XScriptBotDescriptor> copiedBots = original.getBots().stream()
                .map(bot -> new XScriptBotDescriptor(bot.getId(), bot.getVersion(), bot.getName(), bot.getLabel(), bot.getGroup(), bot.getDescription()))
                .collect(Collectors.toList());

        return new XScriptDescriptor(original.getName(), original.getType(),original.getVersion(), copiedVariables, copiedSignals, copiedBots);
    }

    public static void main(String[] args) {
        // Example usage
        XScriptDescriptor original = new XScriptDescriptor("ExampleScript", "1.0.0", "example",
                List.of(new XScriptVariableDescriptor("1.0.0", 1, "Variable1", "Label1", "Group1", "Format1")),
                List.of(new XScriptSignalDescriptor(1, "Signal1", "Label1", "Group1", "1.0.0")),
                List.of(new XScriptBotDescriptor(1, "3.3.3", "name", "label", "group", "dec")));

        XScriptDescriptor copied = copy(original);

        System.out.println("Original Model:");
        System.out.println(XScriptModelFormatter.format(original));

        System.out.println("Copied Model:");
        System.out.println(XScriptModelFormatter.format(copied));
    }
}
