package com.dunkware.trade.boker.api;

public class BrokerOrderException extends Exception {

	private static final long serialVersionUID = 7791107469953536296L;

	public BrokerOrderException(String s) { 
		super(s);
	}
	
	public BrokerOrderException(String s, Throwable t) { 
		super(s,t);
	}
}
