package com.dunkware.trade.net.data.server.stream.signals.session;

import java.time.LocalTime;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamSessionSignalTypeBean extends ObservableBean {

	private String signalName; 
	private String signalGroup; 
	private long signalId; 
	private int signalCount; 
	private String lastEntity; 
	private LocalTime lastTrigger;
	private int entityCount;
	
	public String getSignalName() {
		return signalName;
	}
	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}
	public String getSignalGroup() {
		return signalGroup;
	}
	public void setSignalGroup(String signalGroup) {
		this.signalGroup = signalGroup;
	}
	public long getSignalId() {
		return signalId;
	}
	public void setSignalId(long signalId) {
		this.signalId = signalId;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public String getLastEntity() {
		return lastEntity;
	}
	public void setLastEntity(String lastEntity) {
		this.lastEntity = lastEntity;
	}
	public LocalTime getLastTrigger() {
		return lastTrigger;
	}
	public void setLastTrigger(LocalTime lastTrigger) {
		this.lastTrigger = lastTrigger;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	} 
	
	
	
}
