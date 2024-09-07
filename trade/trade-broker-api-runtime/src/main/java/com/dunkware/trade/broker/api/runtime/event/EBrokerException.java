package com.dunkware.trade.broker.api.runtime.event;

import com.dunkware.trade.broker.api.runtime.Broker;

public class EBrokerException extends EBrokerEvent {

	
	public EBrokerException(Broker broker, String exception) { 
		super(broker);
	}
}
