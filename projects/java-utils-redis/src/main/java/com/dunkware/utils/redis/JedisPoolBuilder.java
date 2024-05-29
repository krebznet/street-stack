package com.dunkware.utils.redis;

import java.net.URI;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolBuilder {

	
	public static JedisPoolBuilder newBuilder() { 
		return new JedisPoolBuilder();
	}
	
	
	private String server; 
	private int port; 
	
	public JedisPoolBuilder serverAndPort(String server, int port) { 
		this.server = server;
		this.port = port;
		return this;
	}
	
	public JedisPool build() throws Exception { 
		JedisPoolConfig c = new JedisPoolConfig();
		
		JedisPool pool = new JedisPool(c, server, port, 5000);
		return pool;
	}
	
}
