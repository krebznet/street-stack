package com.dunkware.trade.engine.api;

import java.util.List;

import com.dunkware.trade.broker.api.Order;
import com.dunkware.trade.engine.api.context.TradeContext;
import com.dunkware.utils.core.events.DunkEventNode;

public interface TradeEngine {
	
	DunkEventNode getEventNode();
	
	TradeContext getContext();
	
	List<Trade> getTrades();
	
	List<Order> getOrders();
	
	

}
