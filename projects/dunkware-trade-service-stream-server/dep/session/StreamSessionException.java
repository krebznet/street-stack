package com.dunkware.trade.service.stream.server.controller.session;

public class StreamSessionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2728344968591202189L;

	public StreamSessionException(String s) { 
		super(s);
	}
	
	public StreamSessionException(String s, Throwable t) {
		super(s,t);
	}
}
