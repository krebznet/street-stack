package com.dunkware.trade.service.beach.model.system.common;

public class TradeExitOrderData {

	private TradeType type; 
	private boolean pegOffsetEnabled = false; 
	private double pegOffset;
	
	public TradeType getType() {
		return type;
	}
	public void setType(TradeType type) {
		this.type = type;
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
