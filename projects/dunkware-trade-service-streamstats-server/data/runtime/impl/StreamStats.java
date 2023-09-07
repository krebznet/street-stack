package com.dunkware.trade.net.service.streamstats.server.runtime.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsConfigStream;
import com.dunkware.trade.net.service.streamstats.server.statcache.StreamStatsCache;
import com.dunkware.trade.net.service.streamstats.server.statstore.StreamStatsStore;

import io.vertx.core.Future;
import io.vertx.core.Promise;

public class StreamStats {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("streamstats");
	
	private StreamStatsStore store; 
	private StreamStatsCache cache; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private StreamStatsConfigStream configStream; 
	
	public void init(StreamStatsConfigStream config) throws Exception { 
		this.configStream = config;
		store = new StreamStatsStore();
		ac.getAutowireCapableBeanFactory().autowireBean(store);
		store.init(this);;
		StreamStatsCache cache = new StreamStatsCache();
		//cache.in
	}
	
	
	public StreamStatsStore getStore() { 
		return store; 
	}
	
	public StreamStatsCache getCache() { 
		return cache; 
	}
	
	public String getStreamIdentifier() { 
		return configStream.getIdenttifier();
	}
	
	public int getStreamid() { 
		return configStream.getId();
	}
	
	
}
