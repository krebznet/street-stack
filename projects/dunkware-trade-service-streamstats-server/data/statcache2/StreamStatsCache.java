package com.dunkware.trade.net.service.streamstats.server.statcache2;

public interface StreamStatsCache {

	public int getId();
	
	public String getIdentifier();
	
	public StreamEntityStats getEntityStats(int id); 
	
	public StreamEntityStats getEntityStatsi(String ident);

}
