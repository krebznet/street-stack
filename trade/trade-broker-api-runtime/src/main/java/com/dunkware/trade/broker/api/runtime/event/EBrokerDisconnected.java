package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Broker;

public class EBrokerDisconnected extends EBrokerEvent {

	
	public EBrokerDisconnected(Broker broker) { 
		super(broker);
	}
}
