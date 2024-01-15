package com.dunkware.trade.service.stream.ingest.injestors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.dunkware.trade.service.stream.ingest.StreamIngestService;
import com.dunkware.trade.service.stream.ingest.StreamIngestor;
import com.dunkware.trade.service.stream.ingest.anot.AStreamIngestor;
import com.dunkware.xstream.model.snapshot.SnapshotValue;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Pipeline;

@AStreamIngestor
public class RedisStackInjestor extends StreamIngestor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("RedisStackInjestor");
	
	private JedisPooled jedisPooled;
	
	private StreamIngestService service;
	// redis-server - port - and bullshit; 
	

	@Override
	public String getName() {
		return "RedisStack";
	}


	@Override
	public void doStart() throws Exception {
		
		JedisClientConfig config = DefaultJedisClientConfig.builder().socketTimeoutMillis(20000).timeoutMillis(200000).build();
		
			HostAndPort hp =new HostAndPort("testrock1.dunkware.net",31673);
			try {
			
			} catch (Exception e) {
				throw new Exception("creating jedis pooled connection failed " + e.toString());
			}
	        
			
	}





	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean disposed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	private class RedisWriter extends Thread { 
		
		JedisPooled jedis;
		
		public RedisWriter() throws Exception { 
		
		}
		
		public void run() { 
		
			
			
			while(true) { 
				SnapshotValue value = null;
				try {
					value = snapshotQueue.take();
					if(value == null) { 
						logger.error(marker, "Snapshot writer take from q is null");
						continue;
					}
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						dispose();
						return;
					}
					logger.error(marker, "Exception taking snapshot from q in writer " + e.toString());
					continue;
			
				}
				
				
				
				
				
			}
			
			
			
		}
		
		public void dispose() { 
			
		}
		
	}

	
}


