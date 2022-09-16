package com.dunkware.trade.sdk.lib.model.exit.rules;

import com.dunkware.trade.sdk.lib.model.exit.SmartExitRuleType;

public class SmartExitTrailingStop extends SmartExitRuleType {
	
	private double stop = Double.MIN_VALUE;
	private double trail;
	
	public double getStop() {
		return stop;
	}
	public void setStop(double stop) {
		this.stop = stop;
	}
	public double getTrail() {
		return trail;
	}
	public void setTrail(double trail) {
		this.trail = trail;
	}
	
	

	
	

}

