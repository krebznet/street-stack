package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Broker;
import com.dunkware.utils.core.events.DunkEvent;

public class EBrokerEvent extends DunkEvent {

	private Broker<?> broker; 
	
	public EBrokerEvent(Broker<?> broker) { 
		this.broker = broker; 
	}
	
	public Broker<?> getBroker() { 
		return broker; 
	}
}
