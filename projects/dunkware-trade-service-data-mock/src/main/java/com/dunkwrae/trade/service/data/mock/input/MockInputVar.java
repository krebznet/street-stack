package com.dunkwrae.trade.service.data.mock.input;

import com.dunkware.net.proto.core.GDataType;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamVar;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionVarUpdater;

public abstract class MockInputVar {
	
	private String identifier; 
	private int id; 
	private String name; 
	private GDataType dataType;
	private MockSessionVarUpdater varUpdater;
	
	public void init(String identifier, String name, int id, GDataType dataType) { 
		this.name = name;
		this.id = id; 
		this.dataType = dataType; 
		this.identifier = identifier; 
	}

	public String getIdentifier() {
		return identifier;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public GDataType getDataType() {
		return dataType;
	}

	public MockStreamVar getStreamVar() { 
		MockStreamVar var = new MockStreamVar();
		var.setDataType(dataType);
		var.setName(name);
		var.setId(id);
		var.setIdentifier(identifier);
		return var;
	}
	
	public abstract MockSessionVarUpdater buildVarUpdater();
	
	
	
	
}
