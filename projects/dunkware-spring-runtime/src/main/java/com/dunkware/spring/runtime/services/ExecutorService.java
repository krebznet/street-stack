package com.dunkware.spring.runtime.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;

import jakarta.annotation.PostConstruct;

@Service
public class ExecutorService {
	
	 @Value("${runtime.executor.pool.size:20}")
	 private int coreSize; 
	 
	 @Value("${runtime.executor.task.timeout:600}")
	 private long timeout; 
	 

	private DExecutor executor; 
	
	@PostConstruct
	private void init() { 
		executor = new DExecutor(30,30,TimeUnit.SECONDS);
	}
	
	public void execute(Runnable runnable) { 
		executor.execute(runnable);
	}
	
	public DExecutor get() { 
		return executor;
	}
}
