package com.dunkware.trade.engine.model.api;

import java.util.ArrayList;
import java.util.List;

public class TradeSystemType {
	
	private String systemName; 
	private List<TradeValidatorType> validators = new ArrayList<TradeValidatorType>();
	protected List<TradeStrategyType> strategies = new ArrayList<TradeStrategyType>();
	
	

}
