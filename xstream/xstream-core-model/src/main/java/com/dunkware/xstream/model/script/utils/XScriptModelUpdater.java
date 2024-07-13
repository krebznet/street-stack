package com.dunkware.xstream.model.script.utils;
import java.util.ArrayList;
import java.util.Optional;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;

public class XScriptModelUpdater {
    private XScriptModel scriptModel;

    public XScriptModelUpdater(XScriptModel scriptModel) {
        this.scriptModel = scriptModel;
    }

    public XScriptModelUpdater insertVariable(int id, String name, String label, String group, String format) {
        scriptModel.getVariables().add(new XScriptModelVariable("", id, name, label, group, format));
        return this;
    }

    public XScriptModelUpdater insertSignal(int id, String name, String label, String group) {
        scriptModel.getSignals().add(new XScriptModelSignal(id, name, label, group, ""));
        return this;
    }

    public XScriptModelUpdater insertBot(int id, String name, String group) {
        scriptModel.getBots().add(new XScriptModelBot(name, group, ""));
        return this;
    }

    public XScriptModelUpdater deleteVariable(int varId) {
        scriptModel.getVariables().removeIf(var -> var.getId() == varId);
        return this;
    }

    public XScriptModelUpdater deleteVariableByName(String name) {
        scriptModel.getVariables().removeIf(var -> var.getName().equals(name));
        return this;
    }

    public XScriptModelUpdater deleteSignal(int id) {
        scriptModel.getSignals().removeIf(sig -> sig.getId() == id);
        return this;
    }

    public XScriptModelUpdater deleteSignalByName(String name) {
        scriptModel.getSignals().removeIf(sig -> sig.getName().equals(name));
        return this;
    }

    public XScriptModelUpdater deleteBot(int id) {
        scriptModel.getBots().removeIf(bot -> bot.getName().equals(String.valueOf(id)));
        return this;
    }

    public XScriptModelUpdater deleteBotByName(String name) {
        scriptModel.getBots().removeIf(bot -> bot.getName().equals(name));
        return this;
    }

    public XScriptModelUpdater updateVariableLabel(int id, String label) {
        Optional<XScriptModelVariable> variable = scriptModel.getVariables().stream().filter(var -> var.getId() == id).findFirst();
        variable.ifPresent(var -> var.setLabel(label));
        return this;
    }

    public XScriptModelUpdater updateVariableGroup(int id, String group) {
        Optional<XScriptModelVariable> variable = scriptModel.getVariables().stream().filter(var -> var.getId() == id).findFirst();
        variable.ifPresent(var -> var.setGroup(group));
        return this;
    }

    public XScriptModelUpdater updateSignalLabel(int id, String label) {
        Optional<XScriptModelSignal> signal = scriptModel.getSignals().stream().filter(sig -> sig.getId() == id).findFirst();
        signal.ifPresent(sig -> sig.setLabel(label));
        return this;
    }

    public XScriptModelUpdater updateSignalGroup(int id, String group) {
        Optional<XScriptModelSignal> signal = scriptModel.getSignals().stream().filter(sig -> sig.getId() == id).findFirst();
        signal.ifPresent(sig -> sig.setGroup(group));
        return this;
    }

    public XScriptModel getUpdatedScriptModel() {
        return scriptModel;
    }

    public static void main(String[] args) {
        // Example usage
        XScriptModel scriptModel = new XScriptModel("ExampleScript", "1.0.0", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        XScriptModelUpdater updater = new XScriptModelUpdater(scriptModel)
                .insertVariable(1, "Variable1", "Label1", "Group1", "Format1")
                .insertSignal(2, "Signal1", "Label2", "Group2")
                .insertBot(3, "Bot1", "Group3")
                .updateVariableLabel(1, "NewLabel1")
                .updateVariableGroup(1, "NewGroup1")
                .deleteSignal(2)
                .deleteBotByName("Bot1");

        XScriptModel updatedScriptModel = updater.getUpdatedScriptModel();

        System.out.println("Updated Variables:");
        updatedScriptModel.getVariables().forEach(var -> System.out.println(var));

        System.out.println("Updated Signals:");
        updatedScriptModel.getSignals().forEach(sig -> System.out.println(sig));

        System.out.println("Updated Bots:");
        updatedScriptModel.getBots().forEach(bot -> System.out.println(bot));
    }
}
