package com.dunkware.trade.api.broker.event;

import com.dunkware.trade.api.broker.Broker;

public class EBrokerException extends EBrokerEvent {

	
	public EBrokerException(Broker broker, String exception) { 
		super(broker);
	}
}
