package com.dunkware.common.tick.reactor;

public class TickReactorException extends Exception {

	public TickReactorException(String s) {
		super(s);
	}
	
	public TickReactorException(String s, Throwable t) { 
		super(s,t);
	}
}
