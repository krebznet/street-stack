package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Broker;
import com.dunkware.utils.core.events.DunkEvent;

public class EBrokerBeanUpdate extends DunkEvent {

	private Broker<?> broker; 
	
	public EBrokerBeanUpdate(Broker<?> broker) { 
		this.broker = broker; 
	}
	
	public Broker<?> getBroker() { 
		return broker; 
	}
}
