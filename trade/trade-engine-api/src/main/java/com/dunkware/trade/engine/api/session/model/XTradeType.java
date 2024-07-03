package com.dunkware.trade.engine.api.session.model;

import java.util.List;

import com.dunkware.trade.api.data.ticker.Ticker;
import com.dunkware.trade.engine.api.model.XTradeEntryType;
import com.dunkware.trade.engine.api.session.XTradeExit;
import com.dunkware.trade.engine.api.session.XTradeOrder;
import com.dunkware.utils.core.events.DunkEventNode;

public interface XTradeType {
	
	public Ticker getTicker();
	
	public XTradeEntryType getEnryType();
	
	public XTradeExitType getExitType();
	
	public List<XTradeExit> getExits();
	
	public DunkEventNode getEventNode();
		
	public List<XTradeOrder> getOrders();
	
	
	
	

}
