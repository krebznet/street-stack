package com.dunkware.xstream.net.client;

public class ContainerClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContainerClientException(String s) {
		super(s);
	}
	
	public ContainerClientException(String s, Throwable t	 ) {
		super(s,t);
	}
}
