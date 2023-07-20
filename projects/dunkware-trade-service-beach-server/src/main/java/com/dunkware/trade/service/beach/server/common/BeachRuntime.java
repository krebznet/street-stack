package com.dunkware.trade.service.beach.server.common;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;

@Component
public class BeachRuntime {

	@Value("${config.executor.timeout}")
	private int executorTimeout;
	@Value("${config.executor.size}")
	private int executorSize; 
	
	@Value("${config.stream.brokers}")
	private String streamBrokers;

	@Value("${config.stream.identifiers}")
	private String streamIdentifiers;
	
	
	@Value("${config.stream.enable}")
	private boolean streamEnabled; 
	
	private DExecutor executor; 
	private DEventTree eventTree; 
	
	
	
	@PostConstruct
	private void start() { 
		executor = new DExecutor(35, 50, TimeUnit.SECONDS);
		eventTree = DEventTree.newInstance(executor);
	}
	
	
	
	public boolean isStreamEnabled() {
		return streamEnabled;
	}



	public DExecutor getExecutor() {
		return executor;
	}
	
	public DEventTree getEventTree() { 
		return eventTree;
	}
	

	public String getStreamBrokers() {
		return streamBrokers;
	}

	public String getStreamIdentifiers() {
		return streamIdentifiers;
	}

	public static LocalDateTime dateTime() { 
		return LocalDateTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
	}
	
	public static LocalTime time() { 
		return LocalTime.now(DTimeZone.toZoneId(DTimeZone.NewYork));
	}
	
	
}

