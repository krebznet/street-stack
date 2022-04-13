package com.dunkware.xstream.model.script;

import com.dunkware.xstream.model.enums.XScriptDataType;

public class StreamScriptVar {

	private String name; 
	private String identifier;
	private int id;
	private XScriptDataType dataType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public XScriptDataType getDataType() {
		return dataType;
	}

	public void setDataType(XScriptDataType dataType) {
		this.dataType = dataType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

}
