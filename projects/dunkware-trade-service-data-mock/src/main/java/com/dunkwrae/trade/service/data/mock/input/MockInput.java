package com.dunkwrae.trade.service.data.mock.input;

import java.util.ArrayList;
import java.util.List;

import com.dunkwrae.trade.service.data.mock.stream.MockStreamEntity;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamSignalType;
import com.dunkwrae.trade.service.data.mock.stream.MockStreamVar;

public class MockInput {
	
	private int entityCount; 
	private int updateInterval; 
	private String streamIdentifier; 
	private double scriptVersion;
	private MockInputSchedule schedule; 
	private List<MockInputSignal> signalFactories = new ArrayList<MockInputSignal>();
	private List<MockInputVar> vars = new ArrayList<MockInputVar>();
	private List<MockStreamEntity> entities = new ArrayList<MockStreamEntity>();
	private List<MockStreamVar> streamVars = null;
	private List<MockStreamSignalType> signalTypes = null;
	
	public MockInput(String identifier, int entityCount, int updateInterval, double scriptVersion) { 
		this.streamIdentifier = identifier; 
		this.scriptVersion = scriptVersion;
		this.entityCount = entityCount;
		this.updateInterval = updateInterval;
		// create the stream entities; 
		int i = 0; 
		while(i < entityCount) { 
			MockStreamEntity ent = new MockStreamEntity();
			ent.setId(i);
			ent.setIdentifier("ent" + i);
			ent.setName("Entity " + i);
			this.entities.add(ent);
			i++;
		}
	}
	
	public List<MockStreamEntity> getStreamEntities() { 
		return entities;
	}
	
	public void setSchedule(MockInputSchedule schedule) { 
		this.schedule = schedule; 
	}
	
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public int getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}
	
	public boolean hasEntity(String identifier) { 
		for (MockStreamEntity ent : entities) {
			if(ent.getIdentifier().equals(identifier)) { 
				return true;
			}
		}
		return false;
	}
	
	public double getScriptVersion() {
		return scriptVersion;
	}
	public void setScriptVersion(double scriptVersion) {
		this.scriptVersion = scriptVersion;
	}

	public String getStreamIdentifier() {
		return streamIdentifier;
	}

	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}

	public List<MockInputSignal> getSignalFactories() {
		return signalFactories;
	}

	public List<MockInputVar> getVars() {
		return vars;
	}

	public MockInputSchedule getSchedule() {
		return schedule;
	}
	
	public List<MockStreamVar> getStreamVars() { 
		if(streamVars == null) { 
			streamVars = new ArrayList<MockStreamVar>();
			for (MockInputVar inputVar : vars) {
				streamVars.add(inputVar.getStreamVar());
			}
		}
		return streamVars;
		
	}
	
	public List<MockStreamSignalType> getStreamSignalTypes() { 
		if(signalTypes == null) { 
			signalTypes = new ArrayList<MockStreamSignalType>();
			for (MockInputSignal mockInputSignalFactory : signalFactories) {
				signalTypes.add(mockInputSignalFactory.getStreamSignalType());
			}
		}
		return signalTypes;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
