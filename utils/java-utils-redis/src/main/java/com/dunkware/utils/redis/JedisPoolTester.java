package com.dunkware.utils.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

public class JedisPoolTester {
	
	
	private JedisPool pool;
	
	public static AtomicInteger writeCount = new AtomicInteger(0);
	
	public static void main(String[] args) {
		try {
			new JedisPoolTester(15, "testrock1.dunkware.net", 30518);
			while(true) { 
				try {
					Thread.sleep(1000);
					System.out.println(writeCount.get());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	public static JedisPoolTester build(int writers, String host, int port) throws Exception{ 
		return new JedisPoolTester(writers, host, port);
	}
	
	
	//
	
	private List<Writer> writeThreads = new ArrayList<Writer>();
	
	private JedisPoolTester(int writers, String host, int port) throws Exception { 
		try {
			pool = JedisPoolBuilder.newBuilder().serverAndPort(host, port).build();
		} catch (Exception e) {
			throw new Exception("Builder pool failed " + e.toString());
		}
		for(int i = 0; i < writers;i++) {
			Writer writer = new Writer("Writer" + i);
			writeThreads.add(writer);
			writer.start();
		}
		
	}
	
	
	private class Writer extends Thread { 
		
		private String name;
		public Writer(String name) { 
			this.name = name;
		}
		
		public void run() {
			Jedis jedis = pool.getResource();
			int counter = 0;
			while(!interrupted()) { 
				Pipeline pipeline = new Pipeline(jedis);
				for(int i = 0; i < 500; i++) {
					pipeline.set(name + counter , name + counter);
					counter++;
				}
				pipeline.sync();
				pipeline.close();
				writeCount.addAndGet(500);
				
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			
			
			}
			
		}
	}

}
