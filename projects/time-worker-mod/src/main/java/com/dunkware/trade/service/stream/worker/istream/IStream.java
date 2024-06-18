package com.dunkware.trade.service.stream.worker.istream;

import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnection;

import com.dunkware.utils.core.stats.StatRegistry;
import com.dunkware.xstream.api.XStream;

public interface IStream {
	
	public RedissonClient getRedissonClient();
	
	public XStream getXStream();
	
	public StatRegistry getStatRegistry();
	
	
	

}
