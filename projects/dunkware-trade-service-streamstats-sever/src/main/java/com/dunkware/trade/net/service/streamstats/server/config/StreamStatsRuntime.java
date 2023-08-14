package com.dunkware.trade.net.service.streamstats.server.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;

@Service
public class StreamStatsRuntime {

	@Value("${config.stats.streams}")
	private String streams; 
	
	
	private DExecutor executor; 
	
	
	@PostConstruct
	private void init() { 
		executor = new DExecutor(30, 30,TimeUnit.SECONDS);
		
	}
	
	public DExecutor getExecutor() { 
		return executor;
	}
	
	
	public String[] getManagedStreams() { 
		return streams.split(",");
	}
}
