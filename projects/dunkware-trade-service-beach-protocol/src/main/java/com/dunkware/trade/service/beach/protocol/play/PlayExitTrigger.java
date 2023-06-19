	package com.dunkware.trade.service.beach.protocol.play;

public class PlayExitTrigger {
	
	private PlayExitTriggerType type; 
	private String signal; 
	private int timer; 
	private String time; 
	private double unrealizedPL;
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getUnrealizedPL() {
		return unrealizedPL;
	}
	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}
	public PlayExitTriggerType getType() {
		return type;
	}
	public void setType(PlayExitTriggerType type) {
		this.type = type;
	} 
	

	
	
	

}
