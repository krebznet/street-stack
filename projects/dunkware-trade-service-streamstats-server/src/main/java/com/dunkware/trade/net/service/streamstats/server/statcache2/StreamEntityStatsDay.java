package com.dunkware.trade.net.service.streamstats.server.statcache2;

import java.time.LocalDate;

import com.dunkware.xstream.model.stats.entity.EntityStat;
import com.dunkware.xstream.model.stats.entity.EntityStats;

public interface StreamEntityStatsDay {
	
	public LocalDate getDate();
	
	public boolean statExists(int type, int target);
	
	public EntityStat getStat(int type, int target);
	
	public EntityStats getStats();
	
	

}
