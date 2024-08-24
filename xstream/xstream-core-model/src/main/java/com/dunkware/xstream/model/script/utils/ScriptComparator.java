package com.dunkware.xstream.model.script.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;
import com.dunkware.xstream.model.script.release.XScriptUpdate;
import com.dunkware.xstream.model.script.release.XScriptVersion;

public class ScriptComparator {

    public XScriptUpdate compareScripts(XScriptDescriptor oldScript, XScriptDescriptor newScript) {
        XScriptUpdate scriptUpdate = new XScriptUpdate();
        compareSignals(oldScript.getSignals(), newScript.getSignals(), scriptUpdate.getChanges());
        compareVariables(oldScript.getVariables(), newScript.getVariables(), scriptUpdate.getChanges());
        compareBots(oldScript.getBots(), newScript.getBots(), scriptUpdate.getChanges());
        setNewScriptVersion(oldScript, newScript, scriptUpdate);
        return scriptUpdate;
    }

    private void compareSignals(List<XScriptSignalDescriptor> oldSignals, List<XScriptSignalDescriptor> newSignals, List<XScriptUpdate.XScriptChange> changes) {
        Map<Integer, XScriptSignalDescriptor> oldSignalMap = new HashMap<>();
        for (XScriptSignalDescriptor signal : oldSignals) {
            oldSignalMap.put(signal.getId(), signal);
        }

        for (XScriptSignalDescriptor newSignal : newSignals) {
            XScriptSignalDescriptor oldSignal = oldSignalMap.remove(newSignal.getId());
            if (oldSignal == null) {
                changes.add(new XScriptUpdate.XScriptChange(newSignal.getName(), newSignal.getLabel(), newSignal.getId(), XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Insert));
            } else if (!oldSignal.getLabel().equals(newSignal.getLabel()) || !oldSignal.getGroup().equals(newSignal.getGroup())) {
                changes.add(new XScriptUpdate.XScriptChange(newSignal.getName(), newSignal.getLabel(), newSignal.getId(), XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Update));
            }
        }

        for (XScriptSignalDescriptor remainingOldSignal : oldSignalMap.values()) {
            changes.add(new XScriptUpdate.XScriptChange(remainingOldSignal.getName(), remainingOldSignal.getLabel(), remainingOldSignal.getId(), XScriptUpdate.XScriptElementType.Signal, XScriptUpdate.XScriptChangeType.Delete));
        }
    }

    private void compareVariables(List<XScriptVariableDescriptor> oldVariables, List<XScriptVariableDescriptor> newVariables, List<XScriptUpdate.XScriptChange> changes) {
        Map<Integer, XScriptVariableDescriptor> oldVariableMap = new HashMap<>();
        for (XScriptVariableDescriptor variable : oldVariables) {
            oldVariableMap.put(variable.getId(), variable);
        }

        for (XScriptVariableDescriptor newVariable : newVariables) {
            XScriptVariableDescriptor oldVariable = oldVariableMap.remove(newVariable.getId());
            if (oldVariable == null) {
                changes.add(new XScriptUpdate.XScriptChange(newVariable.getName(), newVariable.getLabel(), newVariable.getId(), XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Insert));
            } else if (!oldVariable.getLabel().equals(newVariable.getLabel()) || !oldVariable.getGroup().equals(newVariable.getGroup())) {
                changes.add(new XScriptUpdate.XScriptChange(newVariable.getName(), newVariable.getLabel(), newVariable.getId(), XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Update));
            }
        }

        for (XScriptVariableDescriptor remainingOldVariable : oldVariableMap.values()) {
            changes.add(new XScriptUpdate.XScriptChange(remainingOldVariable.getName(), remainingOldVariable.getLabel(), remainingOldVariable.getId(), XScriptUpdate.XScriptElementType.Variable, XScriptUpdate.XScriptChangeType.Delete));
        }
    }

    private void compareBots(List<XScriptBotDescriptor> oldBots, List<XScriptBotDescriptor> newBots, List<XScriptUpdate.XScriptChange> changes) {
        Map<String, XScriptBotDescriptor> oldBotMap = new HashMap<>();
        for (XScriptBotDescriptor bot : oldBots) {
            oldBotMap.put(bot.getName(), bot);
        }

        for (XScriptBotDescriptor newBot : newBots) {
            XScriptBotDescriptor oldBot = oldBotMap.remove(newBot.getName());
            if (oldBot == null) {
                changes.add(new XScriptUpdate.XScriptChange(newBot.getName(), "", 0, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Insert));
            } else if (!oldBot.getGroup().equals(newBot.getGroup())) {
                changes.add(new XScriptUpdate.XScriptChange(newBot.getName(), "", 0, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Update));
            }
        }

        for (XScriptBotDescriptor remainingOldBot : oldBotMap.values()) {
            changes.add(new XScriptUpdate.XScriptChange(remainingOldBot.getName(), "", 0, XScriptUpdate.XScriptElementType.Bot, XScriptUpdate.XScriptChangeType.Delete));
        }
    }

    private void setNewScriptVersion(XScriptDescriptor oldScript, XScriptDescriptor newScript, XScriptUpdate scriptUpdate) {
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
        XScriptDescriptor oldScript = new XScriptDescriptor("ExampleScript", "1.3.0","example", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        XScriptDescriptor newScript = new XScriptDescriptor("ExampleScript", "eampd", "1.3.3", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

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
