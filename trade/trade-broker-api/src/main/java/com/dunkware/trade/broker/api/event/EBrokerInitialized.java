package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Broker;
import com.dunkware.utils.core.events.DunkEvent;

public class EBrokerInitialized extends DunkEvent {

	private Broker broker; 
	
	public EBrokerInitialized(Broker broker) { 
		this.broker = broker; 
	}
	
	public Broker getBroker() { 
		return broker; 
	}
}
