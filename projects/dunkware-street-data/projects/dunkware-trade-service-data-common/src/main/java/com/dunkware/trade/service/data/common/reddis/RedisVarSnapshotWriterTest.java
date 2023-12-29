package com.dunkware.trade.service.data.common.reddis;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;



import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.timeseries.AggregationType;
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
//	new RedisVarSnapshotWriterTest();
		
		LocalDateTime start = LocalDateTime.of(2023, 12,1,6,29);
		LocalDateTime stop = LocalDateTime.of(2023, 12, 27,6,36,14);
//		long startMil = DunkTime.toMilliseconds(start);
//		long stopMil = DunkTime.toMilliseconds(stop);
		new RedisVarSnapshotWriterTest("VSADD:VS:US_EQUITY:7534:40304", 1703836661000L,1703838787000L);
}
	
	
	private JedisPooled jedis;
	
	
	public RedisVarSnapshotWriterTest(String key, long start, long stop) { 
		Jedis pooled = null;
		try {
			//JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			JedisClientConfig config = DefaultJedisClientConfig.builder().socketTimeoutMillis(10000).build();
			
			UnifiedJedis unified = new UnifiedJedis(new HostAndPort("testrock1.dunkware.net", 31673), config);
//			unified.pipelined()
			//JedisPool jedisPool = new JedisPool(jedisPoolConfig, "testrock1.dunkware.net", 31673, 10000);

			//pooled = new JedisPooled(null, key, 0, 0, 0, 0, key, key, 0, key)
					TSMRangeParams mrp1 = new TSMRangeParams();
			mrp1.fromTimestamp(start);
			mrp1.toTimestamp(stop);
			mrp1.filter("entity=7534");
			mrp1.filter("var=3");
			
			Map<String,TSMRangeElements> fuckers = unified.tsMRange(mrp1);
			for (String keyFuck: fuckers.keySet()) {
				TSMRangeElements els = fuckers.get(keyFuck);
				for (TSElement el : els.getValue()) {
					System.out.println(el.getValue());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		if(1 == 1) {
			
			return;
			
		}
		
		/*
		 * TSMGetParams p = new TSMGetParams(); p.latest();
		 * 
		 * TSRangeParams rp = new TSRangeParams(); rp.fromTimestamp(start);
		 * rp.toTimestamp(stop); TSMRangeParams mrp = new TSMRangeParams();
		 * mrp.fromTimestamp(start); mrp.toTimestamp(stop);
		 * mrp.filter("stream=us_equity");
		 * 
		 * //mrp.selectedLabels("stream=us_equity"); Map<String,TSMRangeElements>
		 * results = pooled.tsMRange(mrp); System.out.println(results.size()); try {
		 * Thread.sleep(4000); } catch (Exception e) { // TODO: handle exception } for
		 * (String keyName : results.keySet()) { System.out.println(keyName); } for
		 * (String keyName : results.keySet()) { System.out.println(keyName);
		 * TSMRangeElements elements = results.get(keyName);
		 * System.out.println(elements.getElements().size()); try { Thread.sleep(3000);
		 * } catch (Exception e) { // TODO: handle exception } for (TSElement element :
		 * elements.getElements()) { System.out.println(keyName + ":" +
		 * element.getTimestamp() + ":" + element.getValue()); LocalDateTime dt =
		 * DunkTime.toLocalDateTime(new Date(element.getTimestamp()));
		 * System.out.println(DunkTime.format(dt, DunkTime.YYMMDDHHMMSS));
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 */
		
		
		
		
		
		
	}
	/*
	 * public RedisVarSnapshotWriterTest() { try {
	 * 
	 * JedisPooled pooled = new JedisPooled("testrock1.dunkware.net",31673);
	 * 
	 * 
	 * 
	 * 
	 * if(pooled.exists("SPY:SMA2MIN")) { System.out.println("exi ts"); } else {
	 * System.out.println("no exist"); }
	 * 
	 * if(1 == 1) { return; }
	 * 
	 * 
	 * TSCreateParams params = new TSCreateParams(); params.label("Entity","SPY");
	 * 
	 * // pooled.tsCreate("SPY:SMA2MIN",params);
	 * 
	 * LocalDateTime dateTime = LocalDateTime.of(2001, 1, 1, 9, 30, 1); int i = 0;
	 * 
	 * Pipeline pipeline = pooled.pipelined(); i = 0; long firstMilliseconds = 0;
	 * long lastMilliseconds = 0; while(i < 100) { dateTime =
	 * dateTime.plusSeconds(1);
	 * 
	 * long milliseconds = DunkTime.toMilliseconds(dateTime); if(i == 0) {
	 * firstMilliseconds = milliseconds; } lastMilliseconds = milliseconds;
	 * TSCreateParams dd = new TSCreateParams();
	 * 
	 * 
	 * pipeline.tsAdd("SPY:SMA2MIN", milliseconds, 1.5);; i++; } DStopWatch wat =
	 * DStopWatch.create(); wat.start(); pipeline.sync(); wat.stop();
	 * System.out.println("inserted 1000000 entries in " +
	 * wat.getCompletedSeconds());; TSRangeParams p = new TSRangeParams();
	 * p.fromTimestamp(firstMilliseconds); p.toTimestamp(lastMilliseconds);
	 * p.aggregation(AggregationType.SUM, 1);
	 * 
	 * 
	 * List<TSElement> results =
	 * pooled.tsRange("SPY:SMA2MIN",firstMilliseconds,lastMilliseconds); for
	 * (TSElement tsElement : results) { System.out.println(tsElement.getTimestamp()
	 * + " " + tsElement.getValue()); } TSMRangeParams mp = new TSMRangeParams();
	 * mp.withLabels(); //mp.selectedLabels("Entity:SPY");
	 * mp.fromTimestamp(firstMilliseconds); mp.toTimestamp(lastMilliseconds);
	 * mp.filter("Entity=SPY"); mp.aggregation(AggregationType.AVG,lastMilliseconds
	 * = firstMilliseconds); Map<String,TSMRangeElements> hmm = pooled.tsMRange(mp);
	 * System.out.println("getting?"); //List<TSElement> aggs =
	 * pooled.tsRange("AAPL", p); System.out.println("okay here"); //
	 * pooled.tsRange("AAPL", p); System.out.println("okay freaky");
	 * 
	 * 
	 * // yes there is a range -- } catch (JedisException e) { e.printStackTrace();
	 * // TODO: handle exception }
	 * 
	 * 
	 * //jedis.tsAdd(null, 0, 0)
	 * 
	 * 
	 * }
	 */
}
