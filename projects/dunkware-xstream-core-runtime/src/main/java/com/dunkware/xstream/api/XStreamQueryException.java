package com.dunkware.xstream.api;

public class XStreamQueryException extends Exception {

	private static final long serialVersionUID = 1L;

	public XStreamQueryException(String s) { 
		super(s);
	}
	
	public XStreamQueryException(String s, Throwable t) { 
		super(s,t);
	}
}
