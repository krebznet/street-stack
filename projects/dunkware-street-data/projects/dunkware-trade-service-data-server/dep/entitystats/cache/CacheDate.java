package com.dunkware.trade.net.data.server.stream.entitystats.cache;

import java.util.HashMap;

import com.dunkware.xstream.model.stats.entity.EntityStat;

public class CacheDate {

	private long key;
	
	/**
	 * Cache of entities for this date. 
	 */
	private HashMap<Integer,CacheEntity> entities = new HashMap<Integer,CacheEntity>();
	
	public CacheDate(long key) { 
		this.key = key;
	}
	
	public CacheEntity getOrCreate(int id) { 
		CacheEntity ent = entities.get(id);
		if(ent == null) { 
			ent = new CacheEntity(id);
			entities.put(id, ent);
		}
		return ent; 
	}
	
	public EntityStat entityStat(int entityId, int elementId, int statId) { 
		CacheEntity cacheEntity = entities.get(entityId);
		if(cacheEntity == null) { 
			return null;
		}
		CacheElement element = cacheEntity.get(elementId);
		if(element == null) { 
			return null;
		}
		
		return element.getStatValue(statId);
		
	}
	
	public CacheEntity getEntity(int entity) { 
		return entities.get(entity);
	}
	
	
}
