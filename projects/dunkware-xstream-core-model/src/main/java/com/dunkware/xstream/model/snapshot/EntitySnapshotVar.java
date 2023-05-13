package com.dunkware.xstream.model.snapshot;

import java.time.LocalTime;

public class EntitySnapshotVar {
	
	private int id; 
	private boolean resolved = false; 
	private String ident; 
	private Object value;
	private LocalTime time; 
	private String timeString; 
	private int valueCount;
	private Number high; 
	private String highTimeString; 
	private Number low; 
	private String lowTimeString; 
	private int sessions; 
	
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
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getTimeString() {
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public int getValueCount() {
		return valueCount;
	}
	public void setValueCount(int valueCount) {
		this.valueCount = valueCount;
	}
	public Number getHigh() {
		return high;
	}
	public void setHigh(Number high) {
		this.high = high;
	}
	public String getHighTimeString() {
		return highTimeString;
	}
	public void setHighTimeString(String highTimeString) {
		this.highTimeString = highTimeString;
	}
	public Number getLow() {
		return low;
	}
	public void setLow(Number low) {
		this.low = low;
	}
	public String getLowTimeString() {
		return lowTimeString;
	}
	public void setLowTimeString(String lowTimeString) {
		this.lowTimeString = lowTimeString;
	}
	public int getSessions() {
		return sessions;
	}
	public void setSessions(int sessions) {
		this.sessions = sessions;
	} 
	
	
	
	
	
	
	
	
	

}
