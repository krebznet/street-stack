package com.dunkware.trade.net.service.streamstats.server.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StreamStatsRuntime {

	@Value("${config.stats.streams}")
	private String streams; 
	
	
	//private DExecutor executor; 
	
	
	@PostConstruct
	private void init() { 
		//executor = new DExecutor(0)
	}
	
	
	public String[] getManagedStreams() { 
		return streams.split(",");
	}
}
