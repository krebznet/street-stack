package com.dunkwrae.trade.service.data.mock.stream;

import com.dunkware.net.proto.core.GDataType;

public class MockStreamVar {
	
	private String name; 
	private int id; 
	private GDataType dataType; 
	private String identifier;


	public String getName() { 
		return name; 
	}
	
	public int getId() { 
		return id; 
	}

	public GDataType getDataType() {
		return dataType;
	}

	public void setDataType(GDataType dataType) {
		this.dataType = dataType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
