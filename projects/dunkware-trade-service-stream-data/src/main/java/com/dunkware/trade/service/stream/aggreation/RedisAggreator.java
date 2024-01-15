package com.dunkware.trade.service.stream.aggreation;

import java.util.Map;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.time.DunkTimeDuration;
import com.dunkware.trade.service.stream.aggreation.util.StreamKeyMapper;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.timeseries.AggregationType;
import redis.clients.jedis.timeseries.TSMRangeElements;
import redis.clients.jedis.timeseries.TSMRangeParams;

public class RedisAggreator {

	private JedisPooled jedisPooled;
	
	
	public static void main(String[] args) {
		try {
			String host = "testrock1.dunkware.net";
			int port = 32595;
			DunkTimeDuration duration = DunkTime.dayDuration(DTimeZone.NewYork);
			RedisAggreator agg = new RedisAggreator();
			agg.connect(host, port);
			Number wow = agg.varHigh(duration,"us_equity", 8818,40102);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void connect(String host, int port) throws Exception {
		try {
			jedisPooled = new JedisPooled(new HostAndPort(host, port));
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Number varHigh(DunkTimeDuration duration, String stream, int entity, int var) throws Exception { 
		String key = StreamKeyMapper.varSnapshotKey(stream, entity, var);
		if(!jedisPooled.exists(key) ) { 
			throw new Exception("Entity " + entity + " variable " + var + " snapshot key does not exist");
		}
		
		try {
			long startTimestamp = duration.getStartTimestamp();
			long stopTimestamp = duration.getStopTimestamp();
			
			TSMRangeParams params = new TSMRangeParams(startTimestamp,stopTimestamp);
			params.align(startTimestamp);
			params.aggregation(AggregationType.MAX,1000);
			params.filter("var=" + var);
			params.groupBy("Var", AggregationType.MAX.name());
			Map<String,TSMRangeElements> results = jedisPooled.tsMRange(params);
			
			return 0;	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	
		
		
		
		
	}
	
}

