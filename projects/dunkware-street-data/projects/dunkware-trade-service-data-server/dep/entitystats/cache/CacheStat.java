package com.dunkware.trade.net.data.server.stream.entitystats.cache;

import com.dunkware.xstream.model.stats.entity.EntityStat;

public class CacheStat {
	
	private EntityStat value; 
	
	public CacheStat(EntityStat value) { 
		this.value = value;  
	}
	
	public EntityStat getValue() { 
		return value;
	}

}
