package com.dunkware.trade.engine.api.session;

import com.dunkware.trade.broker.api.Order;
import com.dunkware.utils.core.events.DunkEventNode;

public interface XTradeOrder {
	
	Order getOrder();
	
	XTrade getTrade();
	
	DunkEventNode getEventNode();

}
