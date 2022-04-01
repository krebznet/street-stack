package com.dunkware.common.util.json;

public class DJsonException extends Exception {

	private static final long serialVersionUID = -2020292658599298555L;

	public DJsonException(String s) {
		super(s);
	}
	
	public DJsonException(String s, Throwable t) { 
		super(s,t);
	}
}
