package com.dunkware.trade.engine.api;

import java.util.ArrayList;
import java.util.List;

public class TradeValidatorCheck {
	
	public static TradeValidatorCheck instance() {
		return new TradeValidatorCheck();
	}
	
	
	private List<String> blockers = new ArrayList<String>();
	private List<String> validations = new ArrayList<String>();
	
	public boolean isBlocked() { 
		if(blockers.size() > 0) { 
			return true;
		}
		return false; 
	}
	
	public List<String> getBlockes() { 
		return blockers;
	}

	public List<String> getValidations() { 
		return validations;
	}
}
