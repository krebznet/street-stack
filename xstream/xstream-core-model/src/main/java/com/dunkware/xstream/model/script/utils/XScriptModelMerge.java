package com.dunkware.xstream.model.script.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;
import com.dunkware.xstream.model.script.release.XScriptUpdate;
import com.dunkware.xstream.model.script.release.XScriptVersion;

public class XScriptModelMerge {

    public static XScriptDescriptor merge(XScriptDescriptor updated, XScriptDescriptor original, XScriptUpdate update) {
        XScriptDescriptor merged = new XScriptDescriptor();
        merged.setName(original.getName());
        merged.setVersion(original.getVersion()); // This will be updated later

        // Merge variables
        Map<Integer, XScriptVariableDescriptor> mergedVariables = new HashMap<>();
        for (XScriptVariableDescriptor var : original.getVariables()) {
            mergedVariables.put(var.getId(), var);
        }

        for (XScriptVariableDescriptor var : updated.getVariables()) {
            mergedVariables.put(var.getId(), var);
        }

        // Exclude variables marked for deletion
        for (XScriptUpdate.XScriptChange change : update.getChanges()) {
            if (change.getChangeType() == XScriptUpdate.XScriptChangeType.Delete && change.getElementType() == XScriptUpdate.XScriptElementType.Variable) {
                mergedVariables.remove(change.getElementId());
            }
        }

        merged.setVariables(new ArrayList<>(mergedVariables.values()));

        // Merge signals
        Map<Integer, XScriptSignalDescriptor> mergedSignals = new HashMap<>();
        for (XScriptSignalDescriptor sig : original.getSignals()) {
            mergedSignals.put(sig.getId(), sig);
        }

        for (XScriptSignalDescriptor sig : updated.getSignals()) {
            mergedSignals.put(sig.getId(), sig);
        }

        // Exclude signals marked for deletion
        for (XScriptUpdate.XScriptChange change : update.getChanges()) {
            if (change.getChangeType() == XScriptUpdate.XScriptChangeType.Delete && change.getElementType() == XScriptUpdate.XScriptElementType.Signal) {
                mergedSignals.remove(change.getElementId());
            }
        }

        merged.setSignals(new ArrayList<>(mergedSignals.values()));

        // Merge bots
        Map<String, XScriptBotDescriptor> mergedBots = new HashMap<>();
        for (XScriptBotDescriptor bot : original.getBots()) {
            mergedBots.put(bot.getName(), bot);
        }

        for (XScriptBotDescriptor bot : updated.getBots()) {
            mergedBots.put(bot.getName(), bot);
        }

        // Exclude bots marked for deletion
        for (XScriptUpdate.XScriptChange change : update.getChanges()) {
            if (change.getChangeType() == XScriptUpdate.XScriptChangeType.Delete && change.getElementType() == XScriptUpdate.XScriptElementType.Bot) {
                mergedBots.remove(change.getElementName());
            }
        }

        merged.setBots(new ArrayList<>(mergedBots.values()));

        // Update versions
        XScriptVersion newVersion = XScriptVersion.fromString(original.getVersion());
        boolean hasInsert = update.getChanges().stream().anyMatch(change -> change.getChangeType() == XScriptUpdate.XScriptChangeType.Insert);
        boolean hasDelete = update.getChanges().stream().anyMatch(change -> change.getChangeType() == XScriptUpdate.XScriptChangeType.Delete);
        boolean hasUpdate = update.getChanges().stream().anyMatch(change -> change.getChangeType() == XScriptUpdate.XScriptChangeType.Update);

        if (hasDelete) {
            newVersion = XScriptVersion.incrementMajor(newVersion);
        } else if (hasInsert) {
            newVersion = XScriptVersion.incrementMinor(newVersion);
        } else if (hasUpdate) {
            newVersion = XScriptVersion.incrementRevision(newVersion);
        }

        String newVersionString = newVersion.toString();
        merged.setVersion(newVersionString);

        // Assign new version to new variables, signals, and bots
        for (XScriptVariableDescriptor var : merged.getVariables()) {
            if (var.getVersion() == null || var.getVersion().isEmpty()) {
                var.setVersion(newVersionString);
            }
        }

        for (XScriptSignalDescriptor sig : merged.getSignals()) {
            if (sig.getVersion() == null || sig.getVersion().isEmpty()) {
                sig.setVersion(newVersionString);
            }
        }

        for (XScriptBotDescriptor bot : merged.getBots()) {
            if (bot.getVersion() == null || bot.getVersion().isEmpty()) {
                bot.setVersion(newVersionString);
            }
        }

        return merged;
    }
}
