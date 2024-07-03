package com.dunkware.trade.engine.api.session;

import com.dunkware.trade.broker.api.OrderExecutor;
import com.dunkware.trade.engine.api.model.XTradeType;
import com.dunkware.trade.engine.api.session.bean.XTradeBean;
import com.dunkware.utils.core.events.DunkEventNode;

public interface XTrade {

	XTradeState getState();
	
	XTradeBean getBean();
	
	public XTradeType getType();
	
	public OrderExecutor getOrderExecutor();
	
	DunkEventNode getEventNode();
	
	// trade exit type public List<TradeExit>
	
	
	
}
