package com.dunkware.trade.service.data.common.reddis;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.dunkware.common.util.stopwatch.DStopWatch;
import com.dunkware.common.util.time.DunkTime;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.timeseries.AggregationType;
import redis.clients.jedis.timeseries.DuplicatePolicy;
import redis.clients.jedis.timeseries.TSCreateParams;
import redis.clients.jedis.timeseries.TSElement;
import redis.clients.jedis.timeseries.TSMRangeElements;
import redis.clients.jedis.timeseries.TSMRangeParams;
import redis.clients.jedis.timeseries.TSRangeParams;

public class RedisVarSnapshotWriterTest {

	
	public static void main(String[] args) {
		
		int code = "AAPL".hashCode();
		System.out.println(code);
		int code2 = "SPY".hashCode();
		System.out.println(code2);
		//new RedisVarSnapshotWriterTest();
	}
	
	
	private JedisPooled jedis;
	
	public RedisVarSnapshotWriterTest() {
		try {
			
			JedisPooled pooled = new JedisPooled("testrock1.dunkware.net",31673);
			
			
			TSCreateParams params = new TSCreateParams();
			params.label("Entity","SPY");
			
		//	pooled.tsCreate("SPY:SMA2MIN",params);
			
			LocalDateTime dateTime = LocalDateTime.of(2001, 1, 1, 9, 30, 1);
			int i = 0;
			 
			 Pipeline pipeline = pooled.pipelined(); i = 0;
			 long firstMilliseconds = 0;
			 long lastMilliseconds = 0;
			 while(i < 100) { 
				dateTime = dateTime.plusSeconds(1);
				
				long milliseconds = DunkTime.toMilliseconds(dateTime);
				if(i == 0) { 
					firstMilliseconds = milliseconds;
				} 
				lastMilliseconds = milliseconds;
				TSCreateParams dd = new TSCreateParams();
			
			
				pipeline.tsAdd("SPY:SMA2MIN", milliseconds, 1.5);;
				i++;
			}
			DStopWatch wat = DStopWatch.create();
			wat.start();
			pipeline.sync();
			wat.stop();
			System.out.println("inserted 1000000 entries in " + wat.getCompletedSeconds());;
			TSRangeParams p = new TSRangeParams();
			p.fromTimestamp(firstMilliseconds);
			p.toTimestamp(lastMilliseconds);
			p.aggregation(AggregationType.SUM, 1);
			
			
			List<TSElement> results = pooled.tsRange("SPY:SMA2MIN",firstMilliseconds,lastMilliseconds);
			for (TSElement tsElement : results) {
				System.out.println(tsElement.getTimestamp() + " " + tsElement.getValue());
			}
			TSMRangeParams mp = new TSMRangeParams();
			mp.withLabels();
			//mp.selectedLabels("Entity:SPY");
			mp.fromTimestamp(firstMilliseconds);
			mp.toTimestamp(lastMilliseconds);
			mp.filter("Entity=SPY");
			mp.aggregation(AggregationType.AVG,lastMilliseconds = firstMilliseconds);
			Map<String,TSMRangeElements> hmm = pooled.tsMRange(mp);
			System.out.println("getting?");
			//List<TSElement> aggs = pooled.tsRange("AAPL", p);
			System.out.println("okay here");
		//	pooled.tsRange("AAPL", p);
			System.out.println("okay freaky");
			
			
			// yes there is a range -- 
		} catch (JedisException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		//jedis.tsAdd(null, 0, 0)
		
		
	}
}
