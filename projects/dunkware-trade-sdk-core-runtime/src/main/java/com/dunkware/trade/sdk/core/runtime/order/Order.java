package com.dunkware.trade.sdk.core.runtime.order;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.order.OrderSpec;
import com.dunkware.trade.sdk.core.model.order.OrderStatus;

public interface Order {
	
	OrderStatus getStatus();
		
	public void send() throws OrderException;
	
	public void cancel() throws OrderException;

	public OrderSpec getSpec();

	DEventNode getEventNode();

}
