package com.dunkware.trade.service.stream.server.session;

public class StreamSessionException extends Exception {

	private static final long serialVersionUID = -4566872939118028378L;

	public StreamSessionException(String s) {
		super(s);
	}
	
	public StreamSessionException(String s, Throwable t) { 
		super(s,t);
	}
}
