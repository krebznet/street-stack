package com.dunkware.trade.service.beach.model.webplay;

public class WebEntryOrderData {
	
	private String entryType; 
	private boolean enableEntryTimeout = false; 
	private int entryTimeout;
	private String entryTargetPrice; 
	private int entryChaseInterval; 
	private double entryChaseOffset;
	
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public boolean isEnableEntryTimeout() {
		return enableEntryTimeout;
	}
	public void setEnableEntryTimeout(boolean enableEntryTimeout) {
		this.enableEntryTimeout = enableEntryTimeout;
	}
	public int getEntryTimeout() {
		return entryTimeout;
	}
	public void setEntryTimeout(int entryTimeout) {
		this.entryTimeout = entryTimeout;
	}
	public String getEntryTargetPrice() {
		return entryTargetPrice;
	}
	public void setEntryTargetPrice(String entryTargetPrice) {
		this.entryTargetPrice = entryTargetPrice;
	}
	public int getEntryChaseInterval() {
		return entryChaseInterval;
	}
	public void setEntryChaseInterval(int entryChaseInterval) {
		this.entryChaseInterval = entryChaseInterval;
	}
	public double getEntryChaseOffset() {
		return entryChaseOffset;
	}
	public void setEntryChaseOffset(double entryChaseOffset) {
		this.entryChaseOffset = entryChaseOffset;
	} 
	
	

}
