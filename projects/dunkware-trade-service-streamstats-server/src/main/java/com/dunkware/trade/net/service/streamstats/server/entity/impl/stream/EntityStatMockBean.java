package com.dunkware.trade.net.service.streamstats.server.entity.impl.stream;

import java.time.LocalDate;

public class EntityStatMockBean {

	private int entityCount; 
	private int varCount; 
	private int varStatCount; 
	private int signalCount; 
	private LocalDate startDate;
	private int dayCount;
	
	public int getEntityCount() {
		return entityCount;
	}
	public void setEntityCount(int entityCount) {
		this.entityCount = entityCount;
	}
	public int getVarCount() {
		return varCount;
	}
	public void setVarCount(int varCount) {
		this.varCount = varCount;
	}
	public int getVarStatCount() {
		return varStatCount;
	}
	public void setVarStatCount(int varStatCount) {
		this.varStatCount = varStatCount;
	}
	public int getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(int signalCount) {
		this.signalCount = signalCount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}
	
	
}
