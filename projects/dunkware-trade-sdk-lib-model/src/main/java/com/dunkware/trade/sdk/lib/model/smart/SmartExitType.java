package com.dunkware.trade.sdk.lib.model.smart;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.sdk.core.model.trade.ExitType;

public class SmartExitType extends ExitType {
	
	private List<SmartExitRuleType> rules = new ArrayList<SmartExitRuleType>();

	public List<SmartExitRuleType> getRules() {
		return rules;
	}

	public void setRules(List<SmartExitRuleType> rules) {
		this.rules = rules;
	}
	
	

}
