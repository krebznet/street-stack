package com.dunkware.trade.service.beach.model.system.common;

public class StrategyExitTriggerModel {

	private String name;
	private String type;
	private int timer;
	private double unrealizedPL;
	private boolean isNew = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}
