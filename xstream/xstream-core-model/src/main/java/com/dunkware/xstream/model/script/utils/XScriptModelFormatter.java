package com.dunkware.xstream.model.script.utils;

import java.util.List;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XScriptModelFormatter {

    public static String format(XScriptDescriptor scriptModel) {
        StringBuilder sb = new StringBuilder();

        sb.append("XScriptModel: ").append(scriptModel.getName()).append("\n");
        sb.append("Version: ").append(scriptModel.getVersion()).append("\n");

        sb.append("Variables:\n");
        Map<String, List<XScriptVariableDescriptor>> variablesByGroup = scriptModel.getVariables().stream()
                .collect(Collectors.groupingBy(XScriptVariableDescriptor::getGroup));
        for (Map.Entry<String, List<XScriptVariableDescriptor>> entry : variablesByGroup.entrySet()) {
            sb.append(" Group: ").append(entry.getKey()).append("\n");
            for (XScriptVariableDescriptor variable : entry.getValue()) {
                sb.append(formatVariable(variable)).append("\n");
            }
        }

        sb.append("Signals:\n");
        Map<String, List<XScriptSignalDescriptor>> signalsByGroup = scriptModel.getSignals().stream()
                .collect(Collectors.groupingBy(XScriptSignalDescriptor::getGroup));
        for (Map.Entry<String, List<XScriptSignalDescriptor>> entry : signalsByGroup.entrySet()) {
            sb.append(" Group: ").append(entry.getKey()).append("\n");
            for (XScriptSignalDescriptor signal : entry.getValue()) {
                sb.append(formatSignal(signal)).append("\n");
            }
        }

        sb.append("Bots:\n");
        Map<String, List<XScriptBotDescriptor>> botsByGroup = scriptModel.getBots().stream()
                .collect(Collectors.groupingBy(XScriptBotDescriptor::getGroup));
        for (Map.Entry<String, List<XScriptBotDescriptor>> entry : botsByGroup.entrySet()) {
            sb.append(" Group: ").append(entry.getKey()).append("\n");
            for (XScriptBotDescriptor bot : entry.getValue()) {
                sb.append(formatBot(bot)).append("\n");
            }
        }

        return sb.toString();
    }

    private static String formatVariable(XScriptVariableDescriptor variable) {
        return String.format("  - ID: %d, Name: %s, Label: %s, Format: %s, Version: %s",
                variable.getId(), variable.getName(), variable.getLabel(), variable.getFormat(), variable.getVersion());
    }

    private static String formatSignal(XScriptSignalDescriptor signal) {
        return String.format("  - ID: %d, Name: %s, Label: %s, Version: %s",
                signal.getId(), signal.getName(), signal.getLabel(), signal.getVersion());
    }

    private static String formatBot(XScriptBotDescriptor bot) {
        return String.format("  - Name: %s, Version: %s",
                bot.getName(), bot.getVersion());
    }

    public static void main(String[] args) {
        // Example usage
        XScriptDescriptor scriptModel = new XScriptDescriptor("ExampleScript", "ex000", "1.0.0", 
                List.of(new XScriptVariableDescriptor("1.0.0", 1, "Variable1", "Label1", "Group1", "Format1")),
                List.of(new XScriptSignalDescriptor(1, "Signal1", "Label1", "Group1", "1.0.0")),
                List.of(new XScriptBotDescriptor(1,"3.3", "Bot1", "label", "Group1", "1.0.0")));

        String formattedOutput = format(scriptModel);
        System.out.println(formattedOutput);
    }
}
