package com.dunkware.trade.boker.api;

import com.dunkware.utils.core.events.DunkEventNode;

public interface BrokerAccount {
	
	BrokerOrder createOrder(BrokerOrderType type) throws BrokerOrderException;
	
	BrokerOrderPreview createOrderPreview(BrokerOrderType type) throws BrokerOrderException;
	
	String getIdentifier();
	
	DunkEventNode getEventNode();
	
	Broker getBroker();
	
	BrokerAccountBean getBean();
	
	BrokerAccountStatus getStatus();
	
	void init();
	
	void dispose();

}
