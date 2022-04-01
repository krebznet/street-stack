package com.dunkware.trade.sdk.core.runtime.trade;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.model.trade.TradeType;
import com.dunkware.trade.sdk.core.runtime.order.Order;

public interface TradeContext {
	
	DEventNode getEventNode();
	
	Trade createTrade(TradeType type) throws Exception;

	Order createOrder(OrderType type) throws Exception;
	
	void execute(Runnable runnable);
}
