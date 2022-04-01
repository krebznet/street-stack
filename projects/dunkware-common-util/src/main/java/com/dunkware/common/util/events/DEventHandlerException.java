package com.dunkware.common.util.events;

public class DEventHandlerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784107870542290590L;

	public DEventHandlerException(String s) { 
		super(s);
	}
	
	public DEventHandlerException(String s, Throwable t) {
		super(s,t);
	}
}
