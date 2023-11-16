package com.dunkware.trade.service.data.tester.redis;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@Service
@Profile("Redis")
public class RedisService {

	private JedisCluster cluster; 
	
	@PostConstruct
	void init() { 
		try {
			Jedis jedis = new Jedis("testrock1.dunkware.net", 30379);
			jedis.auth("TaSnanbP5WQirwrw");
		//	jedis.set("test", "world");
			System.out.println(jedis.get("test"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
