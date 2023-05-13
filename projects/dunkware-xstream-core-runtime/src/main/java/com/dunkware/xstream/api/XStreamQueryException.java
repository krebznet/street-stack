package com.dunkware.xstream.api;

public class XQueryException extends Exception {

	private static final long serialVersionUID = 1L;

	public XQueryException(String s) { 
		super(s);
	}
	
	public XQueryException(String s, Throwable t) { 
		super(s,t);
	}
}
