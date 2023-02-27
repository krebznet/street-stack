package com.dunkware.trade.sdk.core.runtime.order;

public class OrderPreview {
	
	private double maxCommission; 
	private double minCommission; 
	private String warning; 
	
	public OrderPreview(double maxCommission, double minCommission, String warning) { 
		this.maxCommission = maxCommission;
		this.minCommission = minCommission;
		this.warning = warning; 
	}
	
	public double maxCommission() { 
		return maxCommission;
	}
	
	public double minCommission() { 
		return minCommission;
	}
	
	public String warning() { 
		return warning; 
	}
}
