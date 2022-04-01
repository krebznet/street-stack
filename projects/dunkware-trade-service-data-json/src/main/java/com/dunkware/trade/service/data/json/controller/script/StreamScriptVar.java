package com.dunkware.trade.service.data.json.controller.script;

import com.dunkware.trade.service.data.json.enums.DataType;

public class StreamScriptVar {

	private String name; 
	private String identifier;
	private int id;
	private DataType dataType;

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

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

}
