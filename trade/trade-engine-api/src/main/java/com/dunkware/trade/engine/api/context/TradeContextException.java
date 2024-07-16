package com.dunkware.trade.engine.api.context;

public class TradeContextException extends Exception {

	private static final long serialVersionUID = 812137848363610300L;

	public TradeContextException(String s) {
		super(s);
	}
	
	public TradeContextException(String s, Exception e) { 
		super(s,e);
	}
}
