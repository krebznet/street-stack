package com.dunkware.utils.redis;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	public static void main(String[] args) {
		try {
			Jedis jedis = new Jedis("testrock1.dunkware.net", 30518);
			//jedis.auth("TaSnanbP5WQirwrw");
		//	jedis.set("test", "world");
			System.out.println(jedis.get("test"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
