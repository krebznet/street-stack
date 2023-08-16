package com.dunkware.trade.net.service.streamstats.server.service.core;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsRuntime;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStats;
import com.dunkware.trade.net.service.streamstats.server.service.StreamStatsService;

//@Service
public class StreamStatsServiceImpl implements StreamStatsService   {
	
	//@Autowired
	//private StreamEntityDayStatsRepo statsRepo;
	
	
	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private StreamStatsRuntime statsRuntime; 
	
	
	
	private ConcurrentHashMap<String,StreamStats> streamStats = new ConcurrentHashMap<String,StreamStats>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("streamservice");
	
	@PostConstruct
	private void init() { 
		Thread runner = new Thread() { 
	
			public void run() { 
				setName("Stream Stats Loader");
				logger.debug(marker, "starting stream service init");
				for (String stream : statsRuntime.getManagedStreams()) {
					StreamStatsImpl streamStats = new StreamStatsImpl();
					try {
						ac.getAutowireCapableBeanFactory().autowireBean(streamStats);
					} catch (Exception e) {
						logger.error(marker,"Exception Autowire StreamStatsImpl " + e.toString());
						continue;
					}
					try {
						logger.debug(marker, "Initializing stream stats for " + stream);
						streamStats.init(stream);
						StreamStatsServiceImpl.this.streamStats.put(stream, streamStats);
						logger.debug(marker, "Initialized stream stats for " + stream);
					} catch (Exception e) {
						logger.error(marker, "Exception Initializing Stream Stats " + e.toString());
						continue;
					}
	
				}
			}
		};
		
		runner.start();
	}
	
	
	@Override
	public StreamStats getStreamStats(String streamIdent) throws Exception {
		if(streamStats.get(streamIdent) == null) 
			throw new Exception("Stream Stats not found for " + streamIdent);
		return streamStats.get(streamIdent);
	}
	

}
