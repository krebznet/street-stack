package com.dunkware.street.broker.lib.api.events;

import com.dunkware.street.broker.lib.api.StreetBroker;

public class EStreetBrokerConnectFail extends EStreetBrokerEvent  {

	private String exception;
	
	public EStreetBrokerConnectFail(StreetBroker broker, String exception) {
		super(broker);
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	

}
