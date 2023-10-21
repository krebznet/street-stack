package com.dunkware.trade.sdk.core.runtime.trade.event;

import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.trade.TradeEntry;

public class ETradeEntryOrderCreated extends ETradeEntry {

	private Order order; 
	
	public ETradeEntryOrderCreated(TradeEntry entry, Order order) {
		super(entry);
		this.order = order; 
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

	
}
