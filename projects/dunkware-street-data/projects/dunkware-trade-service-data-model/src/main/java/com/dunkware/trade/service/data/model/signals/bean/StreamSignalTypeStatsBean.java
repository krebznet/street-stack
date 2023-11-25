package com.dunkware.trade.service.data.model.signals.bean;

import java.time.LocalDateTime;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.observable.ObservableBean;

public class StreamSignalTypeStatsBean extends ObservableBean {

	private int signalId;
	private String signalGroup;
	private String signalName; 
	private int rowId = DRandom.getRandom(0, 99944922); 
	private int signalCount; 
	private int entityCount; 
	private String lastSignalEntityIdent; 
	private int lastSIgnalEntityId; 
	private String lastSignalEntityName; 
	private LocalDateTime lastSignalTime;
	private Number lastSignalPrice; 
	
	
	
	
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public String getSignalGroup() {
		return signalGroup;
	}
	public void setSignalGroup(String signalGroup) {
		this.signalGroup = signalGroup;
	}
	public String getSignalName() {
		return signalName;
	}
	public void setSignalName(String signalName) {
		this.signalName = signalName;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public String getLastSignalEntityIdent() {
		return lastSignalEntityIdent;
	}
	public void setLastSignalEntityIdent(String lastSignalEntityIdent) {
		this.lastSignalEntityIdent = lastSignalEntityIdent;
	}
	public int getLastSIgnalEntityId() {
		return lastSIgnalEntityId;
	}
	public void setLastSIgnalEntityId(int lastSIgnalEntityId) {
		this.lastSIgnalEntityId = lastSIgnalEntityId;
	}
	public String getLastSignalEntityName() {
		return lastSignalEntityName;
	}
	public void setLastSignalEntityName(String lastSignalEntityName) {
		this.lastSignalEntityName = lastSignalEntityName;
	}
	public LocalDateTime getLastSignalTime() {
		return lastSignalTime;
	}
	public void setLastSignalTime(LocalDateTime lastSignalTime) {
		this.lastSignalTime = lastSignalTime;
	}
	public Number getLastSignalPrice() {
		return lastSignalPrice;
	}
	public void setLastSignalPrice(Number lastSignalPrice) {
		this.lastSignalPrice = lastSignalPrice;
	} 
	
	
	
	
	
	
	
	
}
