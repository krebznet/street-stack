package com.dunkware.trade.broker.api.runtime;

import com.dunkware.trade.broker.api.model.order.OrderBean;
import com.dunkware.trade.broker.api.model.order.OrderStatus;
import com.dunkware.trade.broker.api.model.order.OrderType;
import com.dunkware.utils.core.events.DunkEventNode;

public interface Order {

	OrderStatus getStatus();
		
	public void send();
	
	public void cancel() ;

	public OrderBean getBean();

	DunkEventNode getEventNode();
	
	public OrderType getType();
	
	
}
