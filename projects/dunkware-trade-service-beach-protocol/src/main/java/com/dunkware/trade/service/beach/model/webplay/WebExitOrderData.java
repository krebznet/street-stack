package com.dunkware.trade.service.beach.model.webplay;

public class WebExitOrderData {
	
	private String exitType; 
	private String exitTargetPrice; 
	private int exitChaseInterval; 
	private double exitChaseOffSet;
	public String getExitType() {
		return exitType;
	}
	public void setExitType(String exitType) {
		this.exitType = exitType;
	}
	public String getExitTargetPrice() {
		return exitTargetPrice;
	}
	public void setExitTargetPrice(String exitTargetPrice) {
		this.exitTargetPrice = exitTargetPrice;
	}
	public int getExitChaseInterval() {
		return exitChaseInterval;
	}
	public void setExitChaseInterval(int exitChaseInterval) {
		this.exitChaseInterval = exitChaseInterval;
	}
	public double getExitChaseOffSet() {
		return exitChaseOffSet;
	}
	public void setExitChaseOffSet(double exitChaseOffSet) {
		this.exitChaseOffSet = exitChaseOffSet;
	} 
	
	

}
