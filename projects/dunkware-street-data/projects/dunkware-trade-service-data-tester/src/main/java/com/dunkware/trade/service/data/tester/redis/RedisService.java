package com.dunkware.trade.service.data.tester.redis;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.xstream.model.stats.entity.EntityStat;

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
			EntityStat stat = new EntityStat();
			
			int i = 0;
			DStopWatch watch = DStopWatch.create();
			watch.start();
			int count = 0;
			while(i < 50000) { 
				count++;
				if(count == 1000) { 
					System.out.println(i);
					count = 0;
				}
	//			System.out.println(jedis.get("test:" + i));
				jedis.set("test:" + i, "SFDFSDFSDFD");
			//	jedis.set
				i++;
			}
			watch.stop();
			System.out.println(watch.getCompletedSeconds());;
		//	jedis.set("test", "world");
			
			System.out.println(jedis.get("test"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
