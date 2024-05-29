package com.dunkware.stream.data.lib.stats.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class EntityStatIterator {
	
	
	public static EntityStatIterator build(Jedis jedis, int stream, LocalDate date, int days, int entity, int element, int stat) {
		return new EntityStatIterator(jedis, stream, date, days, entity, element, stat);
	}
	
	private int stream;
	private LocalDate date;
	private int days;
	private int entity;
	private int element;
	private int stat;
	
	private JedisPool pool;
	
	private EntityStatDecrementer decrementer; 
	private Jedis jedis;
	
	private String nextKey;
	
	public EntityStatIterator(Jedis jedis, int stream, LocalDate date, int days, int entity, int element, int stat) {
		super();
		this.jedis = jedis;

		this.stream = stream;
		this.date = date;
		this.days = days;
		this.entity = entity;
		this.element = element;
		this.stat = stat;
		decrementer = EntityStatDecrementer.build(stream, date, entity, element, stat);
	}
	
	public List<Double> getList(int size) throws Exception { 
		List<Double> results = new ArrayList<Double>();
		while(hasNext()) { 
			results.add(next());
			if(results.size() == size) { 
				return results;
			}
		}
		throw new Exception("List Unresolvable");
	}
	
	public boolean hasNext() {
		
		for(int i = 0; i < 10; i++) { 
			String nextKey = decrementer.nextKey();
			if(jedis.exists(nextKey)) {
				this.nextKey = nextKey;
				return true;
			}
		}
		return false;
		
	}
	
	public double next() {
		return Double.valueOf(jedis.get(nextKey));
	}
	
	

}
