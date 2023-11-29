package com.dunkware.trade.service.beach.model.system.common;

import java.time.LocalTime;

public class TradeExitTrigger {

	private String name;
	private TradeExitTriggerType type;
	private int timer;
	private double unrealizedPL;
	private String signal;
	private LocalTime time; 
	private double trailingPercent;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public TradeExitTriggerType getType() {
		return type;
	}

	public void setType(TradeExitTriggerType type) {
		this.type = type;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public double getTrailingPercent() {
		return trailingPercent;
	}

	public void setTrailingPercent(double trailingPercent) {
		this.trailingPercent = trailingPercent;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public double getUnrealizedPL() {
		return unrealizedPL;
	}

	public void setUnrealizedPL(double unrealizedPL) {
		this.unrealizedPL = unrealizedPL;
	}


}
