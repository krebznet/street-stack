package com.dunkware.trade.net.data.server.stream.entitystats.cache;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.xstream.model.stats.entity.EntityStat;

public class CacheElement {

	private int key; 
	private Map<Integer,CacheStat> stats = new HashMap<Integer,CacheStat>();

	public CacheElement(int key) { 
		this.key = key; 
	}
	
	public CacheStat get(int stat) { 
		return stats.get(stat);
	}
	
	public EntityStat getStatValue(int stat) { 
		CacheStat cs = stats.get(stat);
		if(cs != null) { 
			return cs.getValue();
		}
		return null;
	}
	
	public void add(EntityStat stat) { 
		CacheStat cacheStat = new CacheStat(stat);
		stats.put(stat.getStat(), cacheStat);
		
	}
}
