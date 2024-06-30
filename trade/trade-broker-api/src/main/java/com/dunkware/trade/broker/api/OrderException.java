package com.dunkware.trade.broker.api;

public class OrderException extends Exception {

	private static final long serialVersionUID = 7791107469953536296L;

	public OrderException(String s) { 
		super(s);
	}
	
	public OrderException(String s, Throwable t) { 
		super(s,t);
	}
}
