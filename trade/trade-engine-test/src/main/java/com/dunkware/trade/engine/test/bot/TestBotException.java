package com.dunkware.trade.engine.test.bot;

public class TestBotException extends Exception {

	
	private static final long serialVersionUID = -4535260532786798090L;

	public TestBotException(String s) { 
		super(s);
	}
	
	public TestBotException(String s, Throwable t) { 
		super(s,t);
	}
}
