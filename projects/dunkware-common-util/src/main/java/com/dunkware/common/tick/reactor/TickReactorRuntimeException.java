package com.dunkware.common.tick.reactor;

public class TickReactorRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1261460503972177281L;

	public TickReactorRuntimeException(String s) {
		super(s);
	}
	
	public TickReactorRuntimeException(String s, Throwable t) {
		super(s,t);
	
	}
	
	
}
