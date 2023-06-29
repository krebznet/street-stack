package com.dunkware.trade.service.stream.server.stats.core;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.stats.StreamStats;
import com.dunkware.trade.service.stream.server.stats.StreamStatsService;

@Service
public class StreamStatsServiceImpl implements StreamStatsService   {
	
	//@Autowired
	//private StreamEntityDayStatsRepo statsRepo;
	
	@Autowired
	private StreamControllerService streamService; 
	
	@Autowired
	private ApplicationContext ac;
	
	private ConcurrentHashMap<String,StreamStats> streamStats = new ConcurrentHashMap<String,StreamStats>();

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("stream.stats");
	
	@EventListener(ApplicationReadyEvent.class)
	private void init() { 
		Thread runner = new Thread() { 
	
			public void run() { 
				for (StreamController stream : streamService.getStreams()) {
					StreamStatsImpl streamStats = new StreamStatsImpl();
					try {
						ac.getAutowireCapableBeanFactory().autowireBean(streamStats);
					} catch (Exception e) {
						logger.error(marker,"Exception Autowire StreamStatsImpl " + e.toString());
						continue;
					}
					try {
						streamStats.init(stream);
						StreamStatsServiceImpl.this.streamStats.put(stream.getName(), streamStats);
					} catch (Exception e) {
						logger.error(marker, "Exception Initializing Stream Stats " + e.toString());
						continue;
					}
					// okay you want to load the stats. 
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
