package com.dunkware.trade.sdk.core.runtime.broker.event;

import com.dunkware.trade.sdk.core.runtime.broker.Broker;

public class EBrokerConnectFail extends EBrokerEvent  {

	private String exception;
	
	public EBrokerConnectFail(Broker broker, String exception) {
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
