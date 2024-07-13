package com.dunkware.xstream.model.script.utils;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;
import com.dunkware.xstream.model.util.XScriptModelFormatter;

public class XScriptModelBuilder {
    private String name;
    private String version;
    private List<XScriptModelVariable> variables = new ArrayList<>();
    private List<XScriptModelSignal> signals = new ArrayList<>();
    private List<XScriptModelBot> bots = new ArrayList<>();

    public XScriptModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public XScriptModelBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public XScriptModelBuilder insertVariable(int id, String name, String label, String group, String format) {
        this.variables.add(new XScriptModelVariable("", id, name, label, group, format));
        return this;
    }

    public XScriptModelBuilder insertSignal(int id, String name, String label, String group) {
        this.signals.add(new XScriptModelSignal(id, name, label, group, ""));
        return this;
    }

    public XScriptModelBuilder insertBot(int id, String name, String group) {
        this.bots.add(new XScriptModelBot(name, group, ""));
        return this;
    }

    public XScriptModel build() {
        return new XScriptModel(name, version, variables, signals, bots);
    }

    public static void main(String[] args) {
        // Example usage
        XScriptModel scriptModel = new XScriptModelBuilder()
                .setName("ExampleScript")
                .setVersion("1.0.0")
                .insertVariable(1, "Variable1", "Label1", "Group1", "Format1")
                .insertSignal(1, "Signal1", "Label1", "Group1")
                .insertBot(1, "Bot1", "Group1")
                .build();

        String formattedOutput = XScriptModelFormatter.format(scriptModel);
        System.out.println(formattedOutput);
    }
}
