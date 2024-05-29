package com.dunkware.utils.redis;

import redis.clients.jedis.Jedis;

public class JedisBuilder {

	public static JedisBuilder newBuilder(String host, int port) { 
		return new JedisBuilder(host,port);
	}
	
	
	private String host; 
	private int port; 
	
	private JedisBuilder(String host, int port) { 
		this.host = host; 
		this.port = port; 
	}
	
	public Jedis build() throws Exception { 
		return new Jedis(host,port);
	}
	
	
}
