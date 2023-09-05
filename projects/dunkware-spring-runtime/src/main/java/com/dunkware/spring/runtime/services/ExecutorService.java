package com.dunkware.spring.runtime.services;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;

@Service
public class ExecutorService {
	
	 @Value("${runtime.executor.pool.size:10}")
	 private int coreSize; 
	 
	 @Value("${runtime.executor.task.timeout:600}")
	 private long timeout; 
	 

	private DExecutor executor; 
	
	@PostConstruct
	private void init() { 
		executor = new DExecutor(coreSize,timeout,TimeUnit.SECONDS);
	}
	
	public void execute(Runnable runnable) { 
		executor.execute(runnable);
	}
	
	public DExecutor get() { 
		return executor;
	}
}
