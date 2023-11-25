package com.dunkware.trade.service.data.model.signals.query;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StreamSignalTypeStatsQuery {
	
	private List<Integer> signalTypes = new ArrayList<Integer>(); 
	private List<Integer> entities = new ArrayList<Integer>();
	private LocalDateTime timeRangeStart = null;
	private LocalDateTime timeRangeEnd = null;
	private boolean timeRangeSession = false;
	
	public List<Integer> getEntities() {
		return entities;
	}
	public void setEntities(List<Integer> entities) {
		this.entities = entities;
	}
	public LocalDateTime getTimeRangeStart() {
		return timeRangeStart;
	}
	public void setTimeRangeStart(LocalDateTime timeRangeStart) {
		this.timeRangeStart = timeRangeStart;
	}
	public LocalDateTime getTimeRangeEnd() {
		return timeRangeEnd;
	}
	public void setTimeRangeEnd(LocalDateTime timeRangeEnd) {
		this.timeRangeEnd = timeRangeEnd;
	}
	
	
	public List<Integer> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<Integer> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public boolean isTimeRangeSession() {
		return timeRangeSession;
	}
	public void setTimeRangeSession(boolean timeRangeSession) {
		this.timeRangeSession = timeRangeSession;
	}
	
	
	
	
	
	
	
	

}
