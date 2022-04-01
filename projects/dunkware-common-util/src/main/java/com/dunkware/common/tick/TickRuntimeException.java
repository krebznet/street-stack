package com.dunkware.common.tick;

public class TickRuntimeException extends RuntimeException  {

	private static final long serialVersionUID = 8217834361355850338L;

	public TickRuntimeException(String s) { 
		super(s);
	}
	
	public TickRuntimeException(String s, Throwable t) { 
		super(s,t);
	}
}
