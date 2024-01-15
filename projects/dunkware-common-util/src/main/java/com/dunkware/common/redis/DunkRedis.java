package com.dunkware.common.redis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.common.util.time.range.DunkDateTimeRange;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.timeseries.AggregationType;
import redis.clients.jedis.timeseries.TSElement;
import redis.clients.jedis.timeseries.TSRangeParams;

public class DunkRedis {
	
	
	public static void main(String[] args) {
		try {
			DunkRedis redis = DunkRedis.instance("testrock1.dunkware.net", 32595);
		//	int[] vars = new int[] {2,3,7030,7005,4012};
			//for (int i : vars) {
				String key = "VS:US_EQUITY:9:5060";
				DunkDateTimeRange timeRange = DunkDateTimeRange.absoluteDate(LocalDate.of(2024,1,5	));
				List<Number> agg = redis.tsAgg(key, timeRange, AggregationType.MAX);
				System.out.println(agg.get(0));
				if(agg.size() > 1) { 
					System.out.println(agg.get(1));
					System.out.println(DunkTime.format(DunkTime.toLocalDateTime(agg.get(1).longValue()),DunkTime.YYYY_MM_DD_HH_MM_SS));
				}	
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DunkRedis instance(JedisPooled pooled) throws Exception {
		return new DunkRedis(pooled);
	}

	public static DunkRedis instance(String host, int port) throws Exception {

		return new DunkRedis(new JedisPooled(host, port));
	}

	private JedisPooled pooled;

	private DunkRedis(JedisPooled pooled) {
		this.pooled = pooled;

	}

	public void close() {
		pooled.close();
	}
	
	
	public List<Number> tsAgg(String key, DunkDateTimeRange timeRange, AggregationType aggType) throws Exception { 
		if (pooled.keys(key).size() == 0) {
			throw new Exception("Key " + key + " not found");
		}
		TSRangeParams rangeParams = new TSRangeParams(timeRange.milisecondsFrom(),timeRange.millsecondsTo());
		rangeParams.aggregation(aggType, timeRange.bucketDuration());
	
		List<TSElement> results = pooled.tsRange(key, rangeParams);
		List<Number> realResults = new ArrayList<Number>();
		realResults.add(results.get(0).getValue());
		realResults.add(results.get(0).getTimestamp());
		return realResults;
		
	}
	
	//get last 10 values set on a ariable and print otu 
	
	
	
	
	public Number tsAgg(String key, LocalDateTime from, LocalDateTime to, AggregationType aggType) throws Exception {
		if (pooled.keys(key).size() == 0) {
			throw new Exception("Key " + key + " not found");
		}

		TSRangeParams rangeParams = new TSRangeParams();
		rangeParams.aggregation(aggType, timeRangeBucketDuration(from, to));
		pooled.tsRange("dd", rangeParams);
		
		return 1;

	}

	public static long timeRangeBucketDuration(LocalDateTime from, LocalDateTime to) {
		return DunkTime.toMilliseconds(to) - DunkTime.toMilliseconds(from);

	}

}
