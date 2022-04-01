package com.dunkware.trade.service.data.json.controller.script;

import com.dunkware.trade.service.data.json.enums.DataType;

public class StreamScriptBuilder {
	
	public static StreamScriptBuilder newBuilder() { 
		return new StreamScriptBuilder();
	}
	
	private StreamScript script = new StreamScript();
	public StreamScriptBuilder setVersion(Double version) { 
		script.setVersion(version);
		return this;
	}
	public StreamScriptBuilder addVar(String identifier, String name, int id,DataType dataType) {
		StreamScriptVar var = new StreamScriptVar();
		var.setDataType(dataType);
		var.setName(name);
		var.setIdentifier(identifier);
		var.setId(id);
		script.getVariables().add(var);
		return this;
	}
	
	public  StreamScriptBuilder addSignalType(String identifier, String name, int id) { 
		StreamSignalType type = new StreamSignalType();
		type.setId(id);
		type.setIdentifier(identifier);
		type.setName(name);
		script.getSignalTypes().add(type);
		return this;
	}
	
	public  StreamScript build() {
		return script;
	}

}
