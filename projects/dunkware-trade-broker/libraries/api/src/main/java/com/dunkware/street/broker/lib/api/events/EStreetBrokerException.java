package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;

public class EStreetBrokerException extends EStreetBrokerEvent {

	private String exception;
	
	public EStreetBrokerException(StreetBroker broker, String exception) {
		super(broker);
		
	}
	
	public String getException() { 
		return exception;
	}

    
	
	
}
