package com.dunkware.trade.service.data.model.signals.bean;

import java.time.LocalDateTime;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamSignalTypeBean extends ObservableBean {

	private int signalId; 
	private String signalName; 
	private String signalGroup; 
	private int signalCount; 
	private LocalDateTime lastSignal; 
	private int lastEntityId; 
	private String lastEntittyName; 
	private String lastEntityIdentifier;
	
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
	public String getSignalGroup() {
		return signalGroup;
	}
	public void setSignalGroup(String signalGroup) {
		this.signalGroup = signalGroup;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public LocalDateTime getLastSignal() {
		return lastSignal;
	}
	public void setLastSignal(LocalDateTime lastSignal) {
		this.lastSignal = lastSignal;
	}
	public int getLastEntityId() {
		return lastEntityId;
	}
	public void setLastEntityId(int lastEntityId) {
		this.lastEntityId = lastEntityId;
	}
	public String getLastEntittyName() {
		return lastEntittyName;
	}
	public void setLastEntittyName(String lastEntittyName) {
		this.lastEntittyName = lastEntittyName;
	}
	public String getLastEntityIdentifier() {
		return lastEntityIdentifier;
	}
	public void setLastEntityIdentifier(String lastEntityIdentifier) {
		this.lastEntityIdentifier = lastEntityIdentifier;
	} 
	
	
}
