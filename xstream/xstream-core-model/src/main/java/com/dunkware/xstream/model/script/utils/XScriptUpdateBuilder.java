package com.dunkware.xstream.model.script.utils;
import java.util.ArrayList;
import java.util.Optional;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;

public class XScriptUpdateBuilder {
    private XScriptDescriptor scriptModel;

    public XScriptUpdateBuilder(XScriptDescriptor scriptModel) {
        this.scriptModel = scriptModel;
    }
    

    public XScriptUpdateBuilder insertVariable(int id, String name, String label, String group, String format) {
        scriptModel.getVariables().add(new XScriptVariableDescriptor("", id, name, label, group, format));
        return this;
    }

    public XScriptUpdateBuilder insertSignal(int id, String name, String label, String group) {
        scriptModel.getSignals().add(new XScriptSignalDescriptor(id, name, label, group, ""));
        return this;
    }
    

    public XScriptUpdateBuilder insertBot(int id,String version, String name, String label, String group, String description) {
        scriptModel.getBots().add(new XScriptBotDescriptor(id,version, name, label, group,description));
        return this;
    }

    public XScriptUpdateBuilder deleteVariable(int varId) {
        scriptModel.getVariables().removeIf(var -> var.getId() == varId);
        return this;
    }

    public XScriptUpdateBuilder deleteVariableByName(String name) {
        scriptModel.getVariables().removeIf(var -> var.getName().equals(name));
        return this;
    }

    public XScriptUpdateBuilder deleteSignal(int id) {
        scriptModel.getSignals().removeIf(sig -> sig.getId() == id);
        return this;
    }

    public XScriptUpdateBuilder deleteSignalByName(String name) {
        scriptModel.getSignals().removeIf(sig -> sig.getName().equals(name));
        return this;
    }

    public XScriptUpdateBuilder deleteBot(int id) {
        scriptModel.getBots().removeIf(bot -> bot.getName().equals(String.valueOf(id)));
        return this;
    }

    public XScriptUpdateBuilder deleteBotByName(String name) {
        scriptModel.getBots().removeIf(bot -> bot.getName().equals(name));
        return this;
    }

    public XScriptUpdateBuilder updateVariableLabel(int id, String label) {
        Optional<XScriptVariableDescriptor> variable = scriptModel.getVariables().stream().filter(var -> var.getId() == id).findFirst();
        variable.ifPresent(var -> var.setLabel(label));
        return this;
    }

    public XScriptUpdateBuilder updateVariableGroup(int id, String group) {
        Optional<XScriptVariableDescriptor> variable = scriptModel.getVariables().stream().filter(var -> var.getId() == id).findFirst();
        variable.ifPresent(var -> var.setGroup(group));
        return this;
    }

    public XScriptUpdateBuilder updateSignalLabel(int id, String label) {
        Optional<XScriptSignalDescriptor> signal = scriptModel.getSignals().stream().filter(sig -> sig.getId() == id).findFirst();
        signal.ifPresent(sig -> sig.setLabel(label));
        return this;
    }

    public XScriptUpdateBuilder updateSignalGroup(int id, String group) {
        Optional<XScriptSignalDescriptor> signal = scriptModel.getSignals().stream().filter(sig -> sig.getId() == id).findFirst();
        signal.ifPresent(sig -> sig.setGroup(group));
        return this;
    }

    public XScriptDescriptor getUpdatedScriptModel() {
        return scriptModel;
    }

    public static void main(String[] args) {
        // Example usage
        XScriptDescriptor scriptModel = new XScriptDescriptor("ExampleScript","type", "1.0.0", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        XScriptUpdateBuilder updater = new XScriptUpdateBuilder(scriptModel)
                .insertVariable(1, "Variable1", "Label1", "Group1", "Format1")
                .insertSignal(2, "Signal1", "Label2", "Group2")
                .insertBot(3, "3.3.3", "Bot1", "label", "d", "Group3")
                .updateVariableLabel(1, "NewLabel1")
                .updateVariableGroup(1, "NewGroup1")
                .deleteSignal(2)
                .deleteBotByName("Bot1");

        XScriptDescriptor updatedScriptModel = updater.getUpdatedScriptModel();

        System.out.println("Updated Variables:");
        updatedScriptModel.getVariables().forEach(var -> System.out.println(var));

        System.out.println("Updated Signals:");
        updatedScriptModel.getSignals().forEach(sig -> System.out.println(sig));

        System.out.println("Updated Bots:");
        updatedScriptModel.getBots().forEach(bot -> System.out.println(bot));
    }
}
