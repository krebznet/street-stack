package com.dunkware.trade.broker.api.event;

import com.dunkware.trade.broker.api.Broker;

public class EBrokerException extends EBrokerEvent {

	
	public EBrokerException(Broker broker, String exception) { 
		super(broker);
	}
}
