package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Broker;

public class EBrokerBeanUpdate   {

	private Broker broker; 
	
	public EBrokerBeanUpdate(Broker broker) { 
		this.broker = broker; 
	}
	
	public Broker getBroker() { 
		return broker; 
	}
}
