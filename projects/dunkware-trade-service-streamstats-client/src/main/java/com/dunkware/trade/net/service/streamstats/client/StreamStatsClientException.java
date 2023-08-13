package com.dunkware.trade.net.service.streamstats.client;

public class StreamStatsClientException extends Exception  {

	
	private static final long serialVersionUID = -4680081007921042588L;

	public StreamStatsClientException(String s) { 
		super(s);
	}
	
	public StreamStatsClientException(String s, Throwable t) {
		super(s,t);
	}
	
}
