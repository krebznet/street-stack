package com.dunkware.time.cache.redis;

import redis.clients.jedis.Jedis;

public class TimeCacheEntity {
	
	private Jedis jedis; 
	private int entityType; 
	private int entityId; 
	
	public TimeCacheEntity(Jedis jedis, int entityType, int entityId) { 
		this.jedis = jedis; 
		this.entityId = entityId; 
		this.entityType = entityType; 
	}
	
	
	

}
	