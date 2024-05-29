package com.dunkware.utils.core.events;

public class DunkEventException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1784107870542290590L;

	public DunkEventException(String s) { 
		super(s);
	}
	
	public DunkEventException(String s, Throwable t) {
		super(s,t);
	}
}
