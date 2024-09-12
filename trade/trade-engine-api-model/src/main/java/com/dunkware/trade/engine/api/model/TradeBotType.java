package com.dunkware.trade.engine.api.model;

import java.util.List;

import com.dunkware.trade.engine.api.model.play.TradePlayType;
import com.dunkware.trade.engine.api.model.validate.TradeValidatorType;

public class TradeBotType {
	
	private double capital; 
	private List<TradePlayType> plays;
	private List<TradeValidatorType> validators;
	private String name; 
	private String description; 

}
