package com.dunkware.trade.service.data.server.spring;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.dunkware.common.util.executor.DExecutor;

@Component
public class ExecutorService {

	private DExecutor exector; 

	@PostConstruct
	private void load() { 
		exector = new DExecutor(20, 400, TimeUnit.SECONDS);
	}
	
	public DExecutor getExecutor() { 
		return exector;
	}
}
