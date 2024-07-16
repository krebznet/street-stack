package com.dunkware.trade.engine.model.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Predefined template within a TradeSystem 
 * it has optianl entry triggers, a entry executor
 * exit executor and exit trigges. as well as 
 * validatoer for throttle and restriciting. 
 * 
 */
public abstract class TradeStrategyType {

	private TradeEntryExecutorType entryExecutor; 
	private TradeExitExecutorType exitExecutor;
	protected List<TradeExitTriggerType> exitTriggers = new ArrayList<TradeExitTriggerType>();
	protected List<TradeEntryTriggerType> entryTrggers = new ArrayList<TradeEntryTriggerType>();
	protected List<TradeValidatorType> validators;

	protected double tradeCapitalAllocation = 300.00;
	

}
