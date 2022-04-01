package com.dunkware.xstream.api;

import com.dunkware.common.util.executor.DExecutor;

public interface XStreamExecutor {
	
	public void execute(Runnable runnable);
	
	public DExecutor getDExecutor();
	
	public void awaitWhileTasksRunning() throws InterruptedException;
	
	public boolean hasActiveTasks();

}
