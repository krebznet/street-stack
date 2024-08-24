package com.dunkware.xstream.model.script.utils;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.model.script.descriptor.XScriptBotDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptSignalDescriptor;
import com.dunkware.xstream.model.script.descriptor.XScriptVariableDescriptor;

public class XScriptModelBuilder {
	
	
	
	public static XScriptModelBuilder instance() { 
		return new XScriptModelBuilder();
	}
    private String name;
    private String version;
    private String type;
    private List<XScriptVariableDescriptor> variables = new ArrayList<>();
    private List<XScriptSignalDescriptor> signals = new ArrayList<>();
    private List<XScriptBotDescriptor> bots = new ArrayList<>();

    public XScriptModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public XScriptModelBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public XScriptModelBuilder insertVariable(String version, int id, String name, String label, String group, String format) {
        this.variables.add(new XScriptVariableDescriptor(version, id, name, label, group, format));
        return this;
    }

    public XScriptModelBuilder insertSignal(int id, String name, String label, String group, String version) {
        this.signals.add(new XScriptSignalDescriptor(id, name, label, group, version));
        return this;
    }

    public XScriptModelBuilder insertBot(int id, String version, String name, String label, String group, String description) {
        this.bots.add(new XScriptBotDescriptor(id, version, name, label, group, description));
        return this;
    }
    
    public XScriptModelBuilder setType(String type) { 
    	this.type = type;
    	return this;
    }

    public XScriptDescriptor build() throws Exception {
    	if(type == null) { 
    		throw new Exception("steam type not set");
    	}
    	if(name == null) { 
    		throw new Exception("steam name not set");
    	}
    	if(version == null) { 
    		throw new Exception("steam version not set");
    	}
    	return new XScriptDescriptor(name,type, version, variables, signals, bots);
    }

    
}
