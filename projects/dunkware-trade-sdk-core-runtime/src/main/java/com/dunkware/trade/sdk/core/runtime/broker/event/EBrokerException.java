package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerException extends EBrokerEvent {

	private String exception;
	
	public EBrokerException(Broker broker, String exception) {
		super(broker);
		
	}
	
	public String getException() { 
		return exception;
	}

	
	
}
