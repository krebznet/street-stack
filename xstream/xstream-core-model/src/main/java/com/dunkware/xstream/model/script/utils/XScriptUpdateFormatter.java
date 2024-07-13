package com.dunkware.xstream.model.script.utils;

import java.util.List;

public class XScriptUpdateFormatter {

    public static String format(XScriptUpdate scriptUpdate) {
        StringBuilder sb = new StringBuilder();

        sb.append("XScriptUpdate:\n");

        if (scriptUpdate.getChanges().isEmpty()) {
            sb.append("  No changes\n");
        } else {
            for (XScriptUpdate.XScriptChange change : scriptUpdate.getChanges()) {
                sb.append(formatChange(change)).append("\n");
            }
        }

        return sb.toString();
    }

    private static String formatChange(XScriptUpdate.XScriptChange change) {
        return String.format(
            "  - Change Type: %s, Element Type: %s, ID: %d, Name: %s, Label: %s",
            change.getChangeType(), change.getElementType(), change.getElementId(), change.getElementName(), change.getElementLabel()
        );
    }

    public static void main(String[] args) {
        // Example usage
        List<XScriptUpdate.XScriptChange> changes = List.of(
            new XScriptUpdate.XScriptChange("Variable1", "Label1", 1, XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Insert),
            new XScriptUpdate.XScriptChange("Signal1", "Label2", 2, XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Update),
            new XScriptUpdate.XScriptChange("Bot1", "", 3, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Delete)
        );

        XScriptUpdate scriptUpdate = new XScriptUpdate(changes);

        String formattedOutput = format(scriptUpdate);
        System.out.println(formattedOutput);
    }
}
