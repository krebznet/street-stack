package com.dunkware.trade.service.web.server.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;

@Service
public class RuntimeConfig {


	
	private DExecutor executor; 
	
	@PostConstruct
	private void postConstruct() { 
		executor = new DExecutor(10);
	}
	
	public DExecutor excutor() { 
		return executor;
	}
	
	

	
	
}
