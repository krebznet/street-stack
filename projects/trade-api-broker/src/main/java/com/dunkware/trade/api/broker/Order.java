package com.dunkware.trade.api.broker;

import com.dunkware.utils.core.events.DunkEventNode;

public interface Order {

	OrderStatus getStatus();
		
	public void send();
	
	public void cancel() ;

	public OrderBean getBean();

	DunkEventNode getEventNode();
	
	public OrderSpec getSpec();
	
	
}
