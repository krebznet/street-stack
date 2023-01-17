package com.dunkware.trade.service.stream.server.controller.session.container;

public class SessionContainerException extends Exception {

	private static final long serialVersionUID = 1L;

	public SessionContainerException(String s) { 
		super(s);
	}
	
	public SessionContainerException(String s, Throwable t) { 
		super(s,t);
	}
}
