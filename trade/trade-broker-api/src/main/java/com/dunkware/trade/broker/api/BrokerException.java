package com.dunkware.trade.broker.api;

public class BrokerException extends Exception {

	private static final long serialVersionUID = 7791107469953536296L;

	public BrokerException(String s) { 
		super(s);
	}
	
	public BrokerException(String s, Throwable t) { 
		super(s,t);
	}
}
