package com.dunkware.trade.engine.api;

import java.util.List;

import com.dunkware.trade.engine.model.trade.TradeBean;
import com.dunkware.utils.core.events.DunkEventNode;

public interface Trade {

	public TradeBean getBean();
	
	public DunkEventNode getEventNode();
	
	public void enterTrade();
	
	public void cancelTrade();
	
	public void exitTrade();
	
	public String getTradeId();
	
	public List<TradeOrder> getOrders();
	
	
}
