package com.dunkware.trade.service.beach.server.session;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;

public interface BeachSessionTradeEntryContainer {
	
	public DEventNode getEvetNode();
	
	public Order createOrder(OrderType type) throws Exception; 
	
	

}
