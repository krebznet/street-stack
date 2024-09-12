package com.dunkware.trade.service.tick.client;

public class TickServiceClientException extends Exception {

	private static final long serialVersionUID = -5043567181107623660L;

	public TickServiceClientException(String s) { 
		super(s);
	}
	
	public TickServiceClientException(String s, Throwable t) { 
		super(s,t);
	}
}
