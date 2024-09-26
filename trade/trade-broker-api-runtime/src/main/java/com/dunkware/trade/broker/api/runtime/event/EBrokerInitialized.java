package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Broker;

public class EBrokerInitialized  {

	private Broker broker; 
	
	public EBrokerInitialized(Broker broker) { 
		this.broker = broker; 
	}
	
	public Broker getBroker() { 
		return broker; 
	}
}
