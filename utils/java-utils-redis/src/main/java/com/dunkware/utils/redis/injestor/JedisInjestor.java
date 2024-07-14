package com.dunkware.utils.redis.injestor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

public class JedisInjestor {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("JedisInjestor");
	private BlockingQueue<Pair<String,String>> queue = new LinkedBlockingQueue<Pair<String,String>>();
	
	public static JedisInjestor newInstance(JedisPool pool) { 
		return new JedisInjestor(pool);
	}
	
	
	private JedisPool pool; 
	private AtomicLong injestCount = new AtomicLong(0);
	private List<Injestor> injestors = new ArrayList<Injestor>();
	private WriteCountLogger countLogger = new WriteCountLogger();
	private JedisInjestor(JedisPool pool) { 
		countLogger = new WriteCountLogger();
		countLogger.start();
		this.pool = pool;
		for(int i = 0; i < 5; i++)  {
			Injestor injestor = new Injestor();
			injestor.start();
			injestors.add(injestor);
		}
	}
	
	
	public void injest(String key, String value) {
		Pair<String,String> v = new Pair<String, String>(key, value);
		queue.add(v);
	}
	
	
	private class Injestor extends Thread { 
		
		Pipeline pipeline; 
		private int batchCount = 0;
		
		public Injestor() { 
			pipeline = new Pipeline(pool.getResource());
		}
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Pair<String,String> keyvalue = queue.poll(2,TimeUnit.SECONDS);
					if(keyvalue == null) { 
						if(batchCount > 0) {
							pipeline.sync();
							injestCount.addAndGet(batchCount);
							batchCount = 0;
							
						}continue;
					}
					pipeline.set(keyvalue.getValue0(),keyvalue.getValue1());
					batchCount++;
					if(batchCount > 500) { 
						batchCount = 0;
						pipeline.sync();
						injestCount.addAndGet(501);
						
					}
				} catch (InterruptedException e) {
					if(batchCount > 0) { 
						try {
							pipeline.sync();	
						} catch (Exception e2) {
							try {
								Thread.sleep(4000);
								pipeline.sync();
							} catch (Exception e3) {
								logger.error(marker, "Exception Syncing Pipeline {}",e.toString(),e);
							
							}
						}
						
						
					}
				}
				
			}
		}
	}
	
	private class WriteCountLogger extends Thread { 
		
		public void run() { 
			while(!interrupted()) { 
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					
				}
				System.out.println(injestCount.get());
			}
		}
	}
}
