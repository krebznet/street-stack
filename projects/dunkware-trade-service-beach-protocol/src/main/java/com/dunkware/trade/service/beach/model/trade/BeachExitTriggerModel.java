package com.dunkware.trade.service.beach.model.trade;

public class BeachExitTriggerModel {
	
	private String type; // TIMER | UPL | SIGNAL | TIME  
	private double upl; 
	private int timer; 
	private String timerUnit; 
	private String signalStream; 
	private String signal;
	private long entityId; 
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getUpl() {
		return upl;
	}
	public void setUpl(double upl) {
		this.upl = upl;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	public String getTimerUnit() {
		return timerUnit;
	}
	public void setTimerUnit(String timerUnit) {
		this.timerUnit = timerUnit;
	}
	public String getSignalStream() {
		return signalStream;
	}
	public void setSignalStream(String signalStream) {
		this.signalStream = signalStream;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	} 
	
	
	
	
	
	

}
