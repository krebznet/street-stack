package com.dunkware.xstream.model.util;

import java.util.List;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XScriptModelFormatter {

    public static String format(XScriptModel scriptModel) {
        StringBuilder sb = new StringBuilder();

        sb.append("XScriptModel: ").append(scriptModel.getName()).append("\n");
        sb.append("Version: ").append(scriptModel.getVersion()).append("\n");

        sb.append("Variables:\n");
        Map<String, List<XScriptModelVariable>> variablesByGroup = scriptModel.getVariables().stream()
                .collect(Collectors.groupingBy(XScriptModelVariable::getGroup));
        for (Map.Entry<String, List<XScriptModelVariable>> entry : variablesByGroup.entrySet()) {
            sb.append(" Group: ").append(entry.getKey()).append("\n");
            for (XScriptModelVariable variable : entry.getValue()) {
                sb.append(formatVariable(variable)).append("\n");
            }
        }

        sb.append("Signals:\n");
        Map<String, List<XScriptModelSignal>> signalsByGroup = scriptModel.getSignals().stream()
                .collect(Collectors.groupingBy(XScriptModelSignal::getGroup));
        for (Map.Entry<String, List<XScriptModelSignal>> entry : signalsByGroup.entrySet()) {
            sb.append(" Group: ").append(entry.getKey()).append("\n");
            for (XScriptModelSignal signal : entry.getValue()) {
                sb.append(formatSignal(signal)).append("\n");
            }
        }

        sb.append("Bots:\n");
        Map<String, List<XScriptModelBot>> botsByGroup = scriptModel.getBots().stream()
                .collect(Collectors.groupingBy(XScriptModelBot::getGroup));
        for (Map.Entry<String, List<XScriptModelBot>> entry : botsByGroup.entrySet()) {
            sb.append(" Group: ").append(entry.getKey()).append("\n");
            for (XScriptModelBot bot : entry.getValue()) {
                sb.append(formatBot(bot)).append("\n");
            }
        }

        return sb.toString();
    }

    private static String formatVariable(XScriptModelVariable variable) {
        return String.format("  - ID: %d, Name: %s, Label: %s, Format: %s, Version: %s",
                variable.getId(), variable.getName(), variable.getLabel(), variable.getFormat(), variable.getVersion());
    }

    private static String formatSignal(XScriptModelSignal signal) {
        return String.format("  - ID: %d, Name: %s, Label: %s, Version: %s",
                signal.getId(), signal.getName(), signal.getLabel(), signal.getVersion());
    }

    private static String formatBot(XScriptModelBot bot) {
        return String.format("  - Name: %s, Version: %s",
                bot.getName(), bot.getVersion());
    }

    public static void main(String[] args) {
        // Example usage
        XScriptModel scriptModel = new XScriptModel("ExampleScript", "1.0.0", 
                List.of(new XScriptModelVariable("1.0.0", 1, "Variable1", "Label1", "Group1", "Format1")),
                List.of(new XScriptModelSignal(1, "Signal1", "Label1", "Group1", "1.0.0")),
                List.of(new XScriptModelBot("Bot1", "Group1", "1.0.0")));

        String formattedOutput = format(scriptModel);
        System.out.println(formattedOutput);
    }
}
