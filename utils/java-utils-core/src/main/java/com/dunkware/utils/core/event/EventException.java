package com.dunkware.utils.core.event;

public class EventException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784107870542290590L;

	public EventException(String s) {
		super(s);
	}
	
	public EventException(String s, Throwable t) {
		super(s,t);
	}
}
