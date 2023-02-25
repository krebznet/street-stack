package com.dunkware.trade.sdk.core.runtime.trade;

import java.util.Collection;
import java.util.stream.Stream;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.Order;

public interface TradeContext {
	
	DEventNode getEventNode();
	
	Collection<Trade> getOpenTrades(); 
	
	void liquidate() throws TradeException;
	
	Trade createTrade(TradeType type) throws Exception;

	Order createOrder(OrderType type) throws Exception;
	
	void execute(Runnable runnable);
	
	Stream getStream(String ident) throws Exception;
	
	void event(String source, String type, String message) throws Exception;
}
