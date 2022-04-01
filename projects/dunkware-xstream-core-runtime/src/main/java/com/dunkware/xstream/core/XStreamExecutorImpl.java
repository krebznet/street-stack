package com.dunkware.xstream.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.api.XStreamExecutor;

public class XStreamExecutorImpl  implements XStreamExecutor {

	private DExecutor executor; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public XStreamExecutorImpl(DExecutor executor) { 
		this.executor = executor;
	}

	@Override
	public void execute(Runnable runnable) {
		try {
			executor.execute(runnable);
		} catch (Exception e) {
			logger.error("Exception Executing Task on DExecutor " + e.toString());
		}
	}

	@Override
	public DExecutor getDExecutor() {
		return executor;
	}

	@Override
	public void awaitWhileTasksRunning() throws InterruptedException {
		executor.awaitWhileTasksRunning();
		
	}

	@Override
	public boolean hasActiveTasks() {
		return executor.hasActiveTasks();
	}
	
	
	
	
	
}
