package com.dunkware.xstream.model.script.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.xstream.model.script.model.XScriptVersion;
import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;
import com.dunkware.xstream.model.script.model.XScriptUpdate;

public class ScriptComparator {

    public XScriptUpdate compareScripts(XScriptModel oldScript, XScriptModel newScript) {
        XScriptUpdate scriptUpdate = new XScriptUpdate();
        compareSignals(oldScript.getSignals(), newScript.getSignals(), scriptUpdate.getChanges());
        compareVariables(oldScript.getVariables(), newScript.getVariables(), scriptUpdate.getChanges());
        compareBots(oldScript.getBots(), newScript.getBots(), scriptUpdate.getChanges());
        setNewScriptVersion(oldScript, newScript, scriptUpdate);
        return scriptUpdate;
    }

    private void compareSignals(List<XScriptModelSignal> oldSignals, List<XScriptModelSignal> newSignals, List<XScriptUpdate.XScriptChange> changes) {
        Map<Integer, XScriptModelSignal> oldSignalMap = new HashMap<>();
        for (XScriptModelSignal signal : oldSignals) {
            oldSignalMap.put(signal.getId(), signal);
        }

        for (XScriptModelSignal newSignal : newSignals) {
            XScriptModelSignal oldSignal = oldSignalMap.remove(newSignal.getId());
            if (oldSignal == null) {
                changes.add(new XScriptUpdate.XScriptChange(newSignal.getName(), newSignal.getLabel(), newSignal.getId(), XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Insert));
            } else if (!oldSignal.getLabel().equals(newSignal.getLabel()) || !oldSignal.getGroup().equals(newSignal.getGroup())) {
                changes.add(new XScriptUpdate.XScriptChange(newSignal.getName(), newSignal.getLabel(), newSignal.getId(), XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Update));
            }
        }

        for (XScriptModelSignal remainingOldSignal : oldSignalMap.values()) {
            changes.add(new XScriptUpdate.XScriptChange(remainingOldSignal.getName(), remainingOldSignal.getLabel(), remainingOldSignal.getId(), XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Delete));
        }
    }

    private void compareVariables(List<XScriptModelVariable> oldVariables, List<XScriptModelVariable> newVariables, List<XScriptUpdate.XScriptChange> changes) {
        Map<Integer, XScriptModelVariable> oldVariableMap = new HashMap<>();
        for (XScriptModelVariable variable : oldVariables) {
            oldVariableMap.put(variable.getId(), variable);
        }

        for (XScriptModelVariable newVariable : newVariables) {
            XScriptModelVariable oldVariable = oldVariableMap.remove(newVariable.getId());
            if (oldVariable == null) {
                changes.add(new XScriptUpdate.XScriptChange(newVariable.getName(), newVariable.getLabel(), newVariable.getId(), XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Insert));
            } else if (!oldVariable.getLabel().equals(newVariable.getLabel()) || !oldVariable.getGroup().equals(newVariable.getGroup())) {
                changes.add(new XScriptUpdate.XScriptChange(newVariable.getName(), newVariable.getLabel(), newVariable.getId(), XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Update));
            }
        }

        for (XScriptModelVariable remainingOldVariable : oldVariableMap.values()) {
            changes.add(new XScriptUpdate.XScriptChange(remainingOldVariable.getName(), remainingOldVariable.getLabel(), remainingOldVariable.getId(), XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Delete));
        }
    }

    private void compareBots(List<XScriptModelBot> oldBots, List<XScriptModelBot> newBots, List<XScriptUpdate.XScriptChange> changes) {
        Map<String, XScriptModelBot> oldBotMap = new HashMap<>();
        for (XScriptModelBot bot : oldBots) {
            oldBotMap.put(bot.getName(), bot);
        }

        for (XScriptModelBot newBot : newBots) {
            XScriptModelBot oldBot = oldBotMap.remove(newBot.getName());
            if (oldBot == null) {
                changes.add(new XScriptUpdate.XScriptChange(newBot.getName(), "", 0, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Insert));
            } else if (!oldBot.getGroup().equals(newBot.getGroup())) {
                changes.add(new XScriptUpdate.XScriptChange(newBot.getName(), "", 0, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Update));
            }
        }

        for (XScriptModelBot remainingOldBot : oldBotMap.values()) {
            changes.add(new XScriptUpdate.XScriptChange(remainingOldBot.getName(), "", 0, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Delete));
        }
    }

    private void setNewScriptVersion(XScriptModel oldScript, XScriptModel newScript, XScriptUpdate scriptUpdate) {
        String currentVersion = oldScript.getVersion();
        boolean hasInsert = scriptUpdate.getChanges().stream().anyMatch(change -> change.getChangeType() == XScriptUpdate.XScriptChangeType.Insert);
        boolean hasDelete = scriptUpdate.getChanges().stream().anyMatch(change -> change.getChangeType() == XScriptUpdate.XScriptChangeType.Delete);
        boolean hasUpdate = scriptUpdate.getChanges().stream().anyMatch(change -> change.getChangeType() == XScriptUpdate.XScriptChangeType.Update);

        XScriptVersion newVersion = XScriptVersion.fromString(currentVersion);

        if (hasDelete) {
            newVersion = XScriptVersion.incrementMajor(newVersion);
        } else if (hasInsert) {
            newVersion = XScriptVersion.incrementMinor(newVersion);
        } else if (hasUpdate) {
            newVersion = XScriptVersion.incrementRevision(newVersion);
        }

        newScript.setVersion(newVersion.toString());
    }

    public static void main(String[] args) {
        // Example usage
        XScriptModel oldScript = new XScriptModel("ExampleScript", "1.3.0","example", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        XScriptModel newScript = new XScriptModel("ExampleScript", "eampd", "1.3.3", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        ScriptComparator comparator = new ScriptComparator();
        XScriptUpdate update = comparator.compareScripts(oldScript, newScript);

        // Print update details
        System.out.println("Changes:");
        for (XScriptUpdate.XScriptChange change : update.getChanges()) {
            System.out.println(change);
        }
        System.out.println("New Script Version: " + newScript.getVersion());
    }
}
