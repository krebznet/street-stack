package com.dunkware.net.trade.service.streamstats.server.service;

public class StreamStatsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StreamStatsException(String s) { 
		super(s);
	}
	
	public StreamStatsException(String s, Throwable t) { 
		super(s,t);
	}
}
