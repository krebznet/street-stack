package com.dunkware.trade.service.beach.server.common;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.events.DEventTree;
import com.dunkware.common.util.executor.DExecutor;

ooooo
public class BeachRuntime {

	@Value("${config.executor.timeout}")
	private int executorTimeout;
	@Value("${config.executor.size}")
	private int executorSize; 
	
	private DExecutor executor; 
	private DEve ntTree eventTree; 
	
	@Value("${config.stream.server}")
	private String streamServer; 
	
	@Value("${config.stream.mock}")
	private boolean streamMock; 
	
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
	
	public String getStreamServerURL() { 
		return streamServer;
	}
	
	public boolean getStreamMock() { 
		return streamMock;
	}
}

