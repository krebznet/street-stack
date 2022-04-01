package com.dunkware.trade.service.beach.server.common;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;

@Component
public class BeachRuntime {

	@Value("${executor.timeout}")
	private int executorTimeout;
	@Value("${executor.size}")
	private int executorSize; 
	
	
	private DExecutor executor; 
	private DEventTree eventTree; 
	
	@PostConstruct
	private void start() { 
		executor = new DExecutor(executorSize, executorTimeout, TimeUnit.SECONDS);
		eventTree = DEventTree.newInstance(executor);
	}
	
	public DExecutor getExecutor() {
		return executor;
	}
	
	public DEventTree getEventTree() { 
		return eventTree;
	}
}

