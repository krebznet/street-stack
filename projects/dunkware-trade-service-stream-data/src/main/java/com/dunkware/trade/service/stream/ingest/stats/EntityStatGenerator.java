package com.dunkware.trade.service.stream.ingest.stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.search.aggr.AggregationBuilder;
import redis.clients.jedis.timeseries.AggregationType;
import redis.clients.jedis.timeseries.TSElement;
import redis.clients.jedis.timeseries.TSRangeParams;

public class EntityStatGenerator {
	
	
	private String key; 
	private JedisPooled jedis;
	private LocalDate date;
	
	
	public static void main(String[] args) {
		
		try {
			JedisPooled pooled = new JedisPooled("testrock1.dunkware.net",30314);
			LocalDate date = LocalDate.now(DTimeZone.toZoneId(DTimeZone.NewYork));
			String streamIdentifier = "us_equity";
			int entityId = 10142;
			int varId = 2;
			
			
			EntityStatGenerator gen = new EntityStatGenerator(pooled,streamIdentifier,entityId,varId,date);
			double high = gen.getHigh();
			System.out.println(high);
		//	gen.printKeys();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
	
	public EntityStatGenerator(JedisPooled pooled, String streamIdentifier, int entityId, int varId, LocalDate date) {
		this.jedis = pooled;
		this.date = date; 
		StringBuilder builder = new StringBuilder();
		builder.append("VS:");
		builder.append(streamIdentifier.toUpperCase());
		builder.append(":");
		builder.append(entityId);
		builder.append(":");
		builder.append(varId);
		key = builder.toString();		
		
		
	}
	
	public void printKeys() { 
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);
		}
		
	}
	
	
	public double getHigh() { 
	
		TSRangeParams params = new TSRangeParams();
		params.fromTimestamp(DunkTime.getDayStartMilliseconds(date));
		params.toTimestamp(DunkTime.getDayEndMilliseconds(date));
		params.aggregation(AggregationType.MAX, 1);
		
		AggregationBuilder b = new AggregationBuilder();
		
		
		List<TSElement> d = jedis.tsRange(key,params);
		double high = 0;
		for (TSElement tsElement : d) {
			if(tsElement.getValue() > high) { 
				high = tsElement.getValue();
			}
			
		}
		System.out.println("element count for high is " + d.size());
		return high;
		
	}
	
	

	 


}
