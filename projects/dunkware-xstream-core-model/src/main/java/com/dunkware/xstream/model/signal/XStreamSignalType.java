package com.dunkware.xstream.model.signal;

import com.dunkware.xstream.model.query.XStreamQueryModel;
import com.dunkware.xstream.model.query.XStreamTimeUnit;

public class XStreamSignalType {
	
	private String identifier; 
	private long id; 
	private XStreamQueryModel query; 
	private boolean enableEntityThrottle = false; 
	private int entityThrottle; 
	private XStreamTimeUnit entityThrrottleTimeUnit; 
	private boolean enableEntityLimit = false; 
	private int entityLimit; 
	private int runInterva = 1;
	private XStreamTimeUnit runIntervalTimeUnit = XStreamTimeUnit.Seconds;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public XStreamQueryModel getQuery() {
		return query;
	}
	public void setQuery(XStreamQueryModel query) {
		this.query = query;
	}
	public boolean isEnableEntityThrottle() {
		return enableEntityThrottle;
	}
	public void setEnableEntityThrottle(boolean enableEntityThrottle) {
		this.enableEntityThrottle = enableEntityThrottle;
	}
	public int getEntityThrottle() {
		return entityThrottle;
	}
	public void setEntityThrottle(int entityThrottle) {
		this.entityThrottle = entityThrottle;
	}
	public XStreamTimeUnit getEntityThrrottleTimeUnit() {
		return entityThrrottleTimeUnit;
	}
	public void setEntityThrrottleTimeUnit(XStreamTimeUnit entityThrrottleTimeUnit) {
		this.entityThrrottleTimeUnit = entityThrrottleTimeUnit;
	}
	public boolean isEnableEntityLimit() {
		return enableEntityLimit;
	}
	public void setEnableEntityLimit(boolean enableEntityLimit) {
		this.enableEntityLimit = enableEntityLimit;
	}
	public int getEntityLimit() {
		return entityLimit;
	}
	public void setEntityLimit(int entityLimit) {
		this.entityLimit = entityLimit;
	}
	public int getRunInterva() {
		return runInterva;
	}
	public void setRunInterva(int runInterva) {
		this.runInterva = runInterva;
	}
	public XStreamTimeUnit getRunIntervalTimeUnit() {
		return runIntervalTimeUnit;
	}
	public void setRunIntervalTimeUnit(XStreamTimeUnit runIntervalTimeUnit) {
		this.runIntervalTimeUnit = runIntervalTimeUnit;
	} 
	
	

}
