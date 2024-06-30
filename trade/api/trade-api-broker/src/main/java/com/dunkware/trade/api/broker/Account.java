package com.dunkware.trade.api.broker;

import com.dunkware.utils.core.events.DunkEventNode;

public interface Account {
	
	Order createOrder(OrderSpec  spec) throws OrderException;
	
	OrderPreview createOrderPreview(OrderType type) throws OrderException;
	
	String getIdentifier();
	
	DunkEventNode getEventNode();
	
	Broker<?> getBroker();
	
	AccountBean getBean();
	
	AccountStatus getStatus();
	
	
	void init();
	
	void dispose();

}
