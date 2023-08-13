package com.dunkware.trade.net.service.streamstats.server.redist;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


public class RedisTest {

	
	public void test() { 
		
	try {
		JedisCluster jedisCluster = new JedisCluster(new HostAndPort("172.16.16.55", 31099));
			   jedisCluster.append("test", "cool");
			   System.out.println(jedisCluster.get("test"));
			   
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	}

}
