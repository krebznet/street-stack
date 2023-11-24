package com.dunkware.trade.service.data.tester.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.stopwatch.DStopWatch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;

@Service
@Profile("Redis")
public class RedisService {

	private JedisCluster cluster; 
	
	private BlockingQueue<Stat> stats = new LinkedBlockingDeque<Stat>();

	@PostConstruct
	void init() { 
		try {
			
			int i = 0;
			
		//	int count = 0;
		//	SpeedMonitor monitor = new SpeedMonitor();
		//	monitor.start();
			
			
			
			Jedis jedis  = new Jedis("testrock1.dunkware.net", 30379);
			jedis.auth("TaSnanbP5WQirwrw");
			
			
			Pipeline pipeline = jedis.pipelined();
			i = 0;
			
			
			DStopWatch watch = DStopWatch.create();
			watch.start();
			List<String> keys = new ArrayList<String>();
			int keyCounter = 0;
			while (i < 500000) {
			    String[] keyValue = new String[2];
			    keyValue[0] = "key" + i;
			    keys.add(keyValue[0]);
			    keyValue[1] = "value" + i;
			    pipeline.sadd(keyValue[0], keyValue[1]);

			    // you can call pipeline.sync() and start new pipeline here if you think there're so much operations in one pipeline
			    i++;
			  }
			  pipeline.sync();
			  watch.stop();
			  System.out.println("wrote" + i + " records in " + watch.getCompletedSeconds());
			  
			  watch.start();
			  try {
				  
				//  jedis.close();
				  
				 // Thread.sleep(5000);
				  //jedis  = new Jedis("testrock1.dunkware.net", 30380);
					//jedis.auth("TaSnanbP5WQirwrw");
					
				  
				  String[] keyArray = keys.toArray(new String[keys.size()]);
				  List<String> values = jedis.mget(keyArray);
				  System.out.println(values.size());
				  watch.stop();
				  System.out.println( " read records in " + watch.getCompletedSeconds());
				  System.out.println(values.get(5));
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			  
			
			  
			  
			
			
			
			
			
			if(1 == 1)  {
				
				System.exit(-1);
			}
			
			// add 100K stats 
			int x = 0; 
			while(x < 10000) { 
				String value = jedis.get("key" + x);
				Stat stat = new Stat();
				stat.value = value;
				stat.key = "key" + x;
				stats.put(stat);;
				x++;
						
				
			}
			/*
			 * while(x < 100000) { Stat stat = new Stat(); stat.key = "key" + x; stat.value
			 * = "key" + x; stats.put(stat);; x++; }
			 * 
			 * x = 0; while(x < 50) { Inserter inserter = new Inserter(); inserter.start();
			 * x++; }
			 */
			
			
		
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private class Stat { 
		
		public String key; 
		public String value; 
	}
	
	
	
	private class Inserter extends Thread { 
		
		public void run() { 
			
			Jedis jedis  = new Jedis("testrock1.dunkware.net", 30379);
			jedis.auth("TaSnanbP5WQirwrw");
			
			while(!interrupted()) { 
				try {
					Stat stat = stats.poll(5, TimeUnit.SECONDS);
					
					if(stat == null) { 
						return;
					}
					
				jedis.set(stat.key, stat.value);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
			
		}
	}
	
	
	private class SpeedMonitor extends Thread { 
		
		public void run() { 

			while(true) { 
				try {
					Thread.sleep(1000);
					System.out.println(stats.size());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		
		
	}
}
