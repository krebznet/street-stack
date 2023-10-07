package com.dunkware.trade.tick.api.feed;

public class TickFeedException extends Exception  {

	private static final long serialVersionUID = 6008665186812782474L;
	
	public TickFeedException(String s) { 
		super(s);
	}

	public TickFeedException(String s, Throwable t) { 
		super(s,t);
	}
}
