package com.dunkware.trade.broker.api;

import com.dunkware.utils.core.events.DunkEventNode;

public interface Order {

	OrderStatus getStatus();
		
	public void send();
	
	public void cancel() ;

	public OrderBean getBean();

	//TODO: AVINASHANV-22 an order has an event node to get call back on order evens
	DunkEventNode getEventNode();
	
	public OrderSpec getSpec();
	
	
}
