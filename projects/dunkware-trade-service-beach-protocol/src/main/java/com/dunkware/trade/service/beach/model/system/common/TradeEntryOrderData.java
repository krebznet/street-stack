package com.dunkware.trade.service.beach.model.system.common;

public class TradeEntryOrderData {

	private TradeType type; 
	private boolean enableEntryTimeout = false; 
	private int entryTimeout; 
	private boolean pegOffsetEnabled = false; 
	private double pegOffset;
	
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
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
	public boolean isPegOffsetEnabled() {
		return pegOffsetEnabled;
	}
	public void setPegOffsetEnabled(boolean pegOffsetEnabled) {
		this.pegOffsetEnabled = pegOffsetEnabled;
	}
	public double getPegOffset() {
		return pegOffset;
	}
	public void setPegOffset(double pegOffset) {
		this.pegOffset = pegOffset;
	} 
	
	
}
