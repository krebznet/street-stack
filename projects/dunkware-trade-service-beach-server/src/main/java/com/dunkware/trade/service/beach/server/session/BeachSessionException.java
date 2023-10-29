package com.dunkware.trade.service.beach.server.session;

public class BeachSessionException extends Exception  {

	private static final long serialVersionUID = 1L;
	
	public BeachSessionException(String s) {
		super(s);
	}
	
	public BeachSessionException(String s, Throwable t) {
		super(s,t);
	}

	
}
