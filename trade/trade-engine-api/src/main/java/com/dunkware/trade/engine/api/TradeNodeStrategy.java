package com.dunkware.trade.engine.api;

import com.dunkware.trade.engine.model.api.node.TradeStrategyType;
import com.dunkware.trade.engine.model.trade.TradeRequest;
import com.dunkware.utils.core.events.DunkEventNode;

import ca.odell.glazedlists.CompositeList;

public interface TradeNodeStrategy {

	public void start(TradeNodeStrategy node, TradeStrategyType type) throws Exception;

	String getName();

	public DunkEventNode getEventNode();

	CompositeList<Trade> getActiveTrades();

	CompositeList<Trade> getTrades();

	CompositeList<Trade> getClosedTrades();

	public void tradeRequest(TradeRequest request);

	public TradeValidatorCheck validateTradeRequest(TradeRequest request);

	public TradeNodeStrategy getParent();
	
	public TradeNodeStrategy createChild(TradeStrategyType type) throws Exception;

	public int getWeight();

	boolean weightEnabled();
}
