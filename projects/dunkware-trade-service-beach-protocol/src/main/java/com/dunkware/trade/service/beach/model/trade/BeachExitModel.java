package com.dunkware.trade.service.beach.model.trade;

public class BeachExitModel {
	
	private String type; // TIMER | UPL | SIGNAL | TIME  
	private double upl; 
	private int timer; 
	private int timerUnit; 
	private String signalStream; 
	private String signal;
	
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
	public int getTimerUnit() {
		return timerUnit;
	}
	public void setTimerUnit(int timerUnit) {
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
	
	

}
