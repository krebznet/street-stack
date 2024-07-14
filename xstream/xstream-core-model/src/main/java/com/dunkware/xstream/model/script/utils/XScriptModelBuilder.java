package com.dunkware.xstream.model.script.utils;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.script.model.XScriptModel;
import com.dunkware.xstream.model.script.model.XScriptModelBot;
import com.dunkware.xstream.model.script.model.XScriptModelSignal;
import com.dunkware.xstream.model.script.model.XScriptModelVariable;

public class XScriptModelBuilder {
	
	
    private String name;
    private String version;
    private String type;
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

    public XScriptModelBuilder insertSignal(int id, String name, String label, String group, String version) {
        this.signals.add(new XScriptModelSignal(id, name, label, group, version));
        return this;
    }

    public XScriptModelBuilder insertBot(int id, String version, String name, String label, String group, String description) {
        this.bots.add(new XScriptModelBot(id, version, name, label, group, description));
        return this;
    }
    
    public XScriptModelBuilder setType(String type) { 
    	this.type = type;
    	return this;
    }

    public XScriptModel build() throws Exception {
    	if(type == null) { 
    		throw new Exception("steam type not set");
    	}
    	if(name == null) { 
    		throw new Exception("steam name not set");
    	}
    	if(version == null) { 
    		throw new Exception("steam version not set");
    	}
    	return new XScriptModel(name,type, version, variables, signals, bots);
    }

    
}
