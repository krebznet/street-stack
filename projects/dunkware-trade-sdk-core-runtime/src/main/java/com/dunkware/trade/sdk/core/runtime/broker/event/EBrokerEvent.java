package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerEvent extends DEvent {
	
	private Broker broker; 
	
	public EBrokerEvent(Broker broker) { 
		this.broker = broker;
	}
	
	public Broker getBroker() { 
		return broker; 
	}
	
	
	

}
