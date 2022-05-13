package com.dunkware.common.mongo;

public class DMongoClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2075339491448548174L;

	public DMongoClientException(String s) { 
		super(s);
	}
	
	public DMongoClientException(String s, Throwable t) { 
		super(s,t);
	}
}
