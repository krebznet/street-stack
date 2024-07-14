package com.dunkware.street.smart.runtime.session;

import java.util.List;

import com.dunkware.street.stream.model.session.TradeCloserType;
import com.dunkware.street.stream.model.session.TradeEntryType;
import com.dunkware.street.stream.model.session.TradeExitType;
import com.dunkware.street.stream.model.session.TradeOpenerType;
import com.dunkware.street.stream.model.session.TradeSpec;

//TODO: AVINASHANV-32 Trade Strategy within Trade Session
/**
 * A trade strategy has the elements that define entry triggers
 * exit triggers, trade validators or constraints. trade opener 
 * that will have a strategy on how to buy the shares to fill the trade
 * and same for trade closer. when we create a trade its same pattern as broker
 * 
 */
public interface TradeStrategy {
	
	public TradeOpenerType getTradeOpenerType();
	
	public TradeCloserType getTradeCloserType();
	
	public String getStrategyName();
	
	public List<Trade> getStrategyTrades();
	
	public List<TradeOrder> getStrategyOrders();
	
	public List<TradeExitType> getExitTypes();
	
	public List<TradeEntryType> getEntryTypes();
	
	public List<TradeValidator> getValidators();

	public void close(Trade trade);
	
	
	public Trade createTrade(TradeSpec type); 
	
}
