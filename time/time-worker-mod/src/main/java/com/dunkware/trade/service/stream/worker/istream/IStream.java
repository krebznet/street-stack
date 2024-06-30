package com.dunkware.trade.service.stream.worker.istream;

import com.dunkware.utils.core.stats.StatRegistry;
import com.dunkware.xstream.api.XStream;

public interface IStream {
	
//	public RedissonClient getRedissonClient();
	
	public XStream getXStream();
	
	public StatRegistry getStatRegistry();
	
	
	

}
