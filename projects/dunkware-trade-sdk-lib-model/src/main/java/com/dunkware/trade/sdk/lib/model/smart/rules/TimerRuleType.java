package com.dunkware.trade.sdk.lib.model.smart.rules;

import com.dunkware.trade.sdk.lib.model.smart.SmartExitRuleType;

public class TimerRuleType extends SmartExitRuleType  {

	private int timer;

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	} 
	
	
}
