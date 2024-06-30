package com.dunkware.xstream.api;

import com.dunkware.utils.core.concurrent.DunkExecutor;

public interface XStreamExecutor {
	
	public void execute(Runnable runnable);
	
	public DunkExecutor getDunkExecutor();
	
	public void awaitWhileTasksRunning() throws InterruptedException;
	
	public boolean hasActiveTasks();

}
