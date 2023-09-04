package com.dunkware.trade.net.service.streamstats.server.runtime;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsConfig;
import com.dunkware.trade.net.service.streamstats.server.config.StreamStatsConfigStream;
import com.dunkware.trade.net.service.streamstats.server.runtime.impl.StreamStatsImpl;

@Service()
public class StreamStatsRuntime {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("streamstats");
	
	@Autowired
	private StreamStatsConfig statsConfig; 
	
	@Autowired
	private ApplicationContext ac; 
	
	private Map<String,StreamStatsImpl> streams = new ConcurrentHashMap<String, StreamStatsImpl>();
	
		
	@PostConstruct
	private void init() { 
		Thread runner = new Thread() { 
			
			public void run() { 
				for (StreamStatsConfigStream configStream : statsConfig.configStreams()) {
					StreamStatsImpl stream = new StreamStatsImpl();
					ac.getAutowireCapableBeanFactory().autowireBean(stream);
					try {
						stream.init(configStream);
						streams.put(configStream.getIdenttifier(), stream);
					} catch (Exception e) {
						logger.error("Exception Initializing StreamStats help! " + e.toString());;
						System.exit(-1);
					}
					
				}
			}
			
		};

		runner.start();
		
	}
	
	public StreamStatsImpl getStreamStats(String ident) throws Exception { 
		return null;
	}
	
	public StreamStatsImpl getStreamStats(int id) throws Exception { 
		return null;
	}

}
