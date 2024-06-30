package com.dunkware.xstream.api;

public class XStreamException extends Exception {

	private static final long serialVersionUID = -8055492181652166372L;

	public XStreamException(String s) {
		super(s);
	}
	
	public XStreamException(String s, Throwable t) {
		super(s,t);
	}
}
