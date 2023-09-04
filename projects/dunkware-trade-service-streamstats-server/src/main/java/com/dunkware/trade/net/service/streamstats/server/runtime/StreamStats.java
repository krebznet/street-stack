package com.dunkware.trade.net.service.streamstats.server.runtime;

import com.dunkware.trade.net.service.streamstats.server.statcache2.StreamStatsCache;
import com.dunkware.trade.net.service.streamstats.server.statstore.StreamStatsStore;

public interface StreamStats {

	StreamStatsStore getStore();
	
	StreamStatsCache getCache();
	
	String getIdent();
	
	int getId();
}
