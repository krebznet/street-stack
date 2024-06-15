package com.dunkware.time.cache.redis;

import redis.clients.jedis.Jedis;

public class TimeCache {

	private Jedis jedis;

	public TimeCache(String host, int port) {
		this.jedis = new Jedis(host, port);
	}
	


}
