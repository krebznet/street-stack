package com.dunkware.xstream.model.signal;

import java.util.Map;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTime;

public class XStreamSignalSpec {
	
	private int signalId; 
	private String signalName;
	private String entityName;; 
	private int entityId; 
	private DTime streamTime;; 
	private DTime realTime;
	private Map<String,Object> vars;
	private DDate date;
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public String getSignalName() {
		return signalName;
	}
	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public DTime getStreamTime() {
		return streamTime;
	}
	public void setStreamTime(DTime streamTime) {
		this.streamTime = streamTime;
	}
	public DTime getRealTime() {
		return realTime;
	}
	public void setRealTime(DTime realTime) {
		this.realTime = realTime;
	}
	public Map<String, Object> getVars() {
		return vars;
	}
	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}
	public DDate getDate() {
		return date;
	}
	public void setDate(DDate date) {
		this.date = date;
	}
	
	

	
	
	
	
	

}
