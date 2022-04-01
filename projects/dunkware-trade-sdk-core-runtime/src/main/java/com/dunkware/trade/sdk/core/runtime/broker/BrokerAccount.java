package com.dunkware.trade.sdk.core.runtime.broker;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.sdk.core.model.broker.BrokerAccountSpec;
import com.dunkware.trade.sdk.core.model.order.OrderType;
import com.dunkware.trade.sdk.core.runtime.order.Order;
import com.dunkware.trade.sdk.core.runtime.order.OrderException;

public interface BrokerAccount {
	
	BrokerAccountSpec getSpec();
	
	Order createOrder(OrderType type) throws OrderException;
	
	String getIdentifier();
	
	DEventNode getEventNode();
	
	Broker getBroker();
	

}
