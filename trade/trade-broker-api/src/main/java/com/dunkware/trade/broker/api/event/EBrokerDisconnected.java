package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Broker;

public class EBrokerDisconnected extends EBrokerEvent {

	
	public EBrokerDisconnected(Broker<?> broker) { 
		super(broker);
	}
}
