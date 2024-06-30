package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Broker;

public class EBrokerDisconnected extends EBrokerEvent {

	
	public EBrokerDisconnected(Broker<?> broker) { 
		super(broker);
	}
}
