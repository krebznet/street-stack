package com.dunkware.xstream.net.client;

public class StreamClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StreamClientException(String s) {
		super(s);
	}
	
	public StreamClientException(String s, Throwable t	 ) {
		super(s,t);
	}
}
