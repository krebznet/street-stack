package com.dunkware.xstream.model.meta;

import com.dunkware.xstream.model.query.XStreamQueryModel;
import com.dunkware.xstream.model.query.XStreamTimeUnit;

public class SignalModel {
	
	private XStreamQueryModel query; 
	private int entitySignalThrottle; 
	private XStreamTimeUnit entitySignalThrottleUnit;
	private int entitySignalLimit;
	private int id; 
	private String ident; 
	private int scanInterval;
	
	public XStreamQueryModel getQuery() {
		return query;
	}
	public void setQuery(XStreamQueryModel query) {
		this.query = query;
	}
	public int getEntitySignalThrottle() {
		return entitySignalThrottle;
	}
	public void setEntitySignalThrottle(int entitySignalThrottle) {
		this.entitySignalThrottle = entitySignalThrottle;
	}
	public XStreamTimeUnit getEntitySignalThrottleUnit() {
		return entitySignalThrottleUnit;
	}
	public void setEntitySignalThrottleUnit(XStreamTimeUnit entitySignalThrottleUnit) {
		this.entitySignalThrottleUnit = entitySignalThrottleUnit;
	}
	public int getEntitySignalLimit() {
		return entitySignalLimit;
	}
	public void setEntitySignalLimit(int entitySignalLimit) {
		this.entitySignalLimit = entitySignalLimit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public int getScanInterval() {
		return scanInterval;
	}
	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	} 
	
	

}
