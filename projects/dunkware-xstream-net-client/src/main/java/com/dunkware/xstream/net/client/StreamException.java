package com.dunkware.xstream.net.client;

public class StreamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StreamException(String s) {
		super(s);
	}
	
	public StreamException(String s, Throwable t	 ) {
		super(s,t);
	}
}
