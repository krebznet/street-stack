package com.dunkware.xstream.api;

public class XStreamRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4002102347135046971L;

	public XStreamRuntimeException(String s) {
		super(s);
	}
	
	public XStreamRuntimeException(String s, Throwable t) {
		super(s,t);
	}
}
