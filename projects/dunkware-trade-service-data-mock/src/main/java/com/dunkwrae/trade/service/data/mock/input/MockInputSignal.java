package com.dunkwrae.trade.service.data.mock.input;

import java.util.ArrayList;
import java.util.List;

import com.dunkwrae.trade.service.data.mock.stream.MockStreamSignalType;
import com.dunkwrae.trade.service.data.mock.stream.session.MockSessionSignalFactory;

public abstract class MockInputSignal {
	
	private String identifier; 
	private String name; 
	private int id;
	
	private List<String> entities = new ArrayList<>();
	
	public void setStreamSignalType(String identifier, String name, int id) { 
		this.id = id; 
		this.identifier = identifier;
		this.name = name;
	}
	
	public void setEntities(List<String> entities) { 
		this.entities = entities;
		
	}
	
	public boolean handleEntity(String identifier) { 
		if(entities.size() == 0) { 
			return true;
		}
		for (String string : entities) {
			if(string.equals(identifier)) { 
				return true;
			}
		}
		return false;
	}
	
	public abstract MockSessionSignalFactory createSignalFactory();
	
	
	public MockStreamSignalType getStreamSignalType() { 
		MockStreamSignalType type = new MockStreamSignalType();
		type.setId(id);
		type.setName(name);
		type.setIdentifier(identifier);
		type.setId(id);
		return type;
	}
	

}
