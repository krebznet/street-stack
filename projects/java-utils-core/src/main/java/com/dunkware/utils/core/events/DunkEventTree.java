package com.dunkware.utils.core.events;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.dunkware.utils.core.concurrent.DunkExecutor;

public class DunkEventTree {
	
	public static final long LOCK_TIMEOUT = 30; 
	public static final TimeUnit LOCK_TIMEOUT_UNIT = TimeUnit.SECONDS;
	
	public static DunkEventTree newInstance(DunkExecutor executor)  { 
		return new DunkEventTree(executor);
	}

	private DunkEventNode rootNode;
	private DunkExecutor executor;
	private long lockTimeout; 
	private TimeUnit lockTimeoutUnit;
	
	private DunkEventTree(DunkExecutor executor) { 
		rootNode = new DunkEventNode(null, "/",this);
		this.executor = executor;
		this.lockTimeout = LOCK_TIMEOUT;
		this.lockTimeoutUnit = LOCK_TIMEOUT_UNIT;
	}
	
	public DunkEventNode getRoot() { 
		return rootNode;
	}
	
	public DunkExecutor getExecutor() { 
		return executor;
	}

	public long getLockTimeout() {
		return lockTimeout;
	}

	public void setLockTimeout(long lockTimeout) {
		this.lockTimeout = lockTimeout;
	}

	public TimeUnit getLockTimeoutUnit() {
		return lockTimeoutUnit;
	}

	public void setLockTimeoutUnit(TimeUnit lockTimeoutUnit) {
		this.lockTimeoutUnit = lockTimeoutUnit;
	}
	
	public boolean tryAcquire(Semaphore semaphore) throws InterruptedException  { 
		return semaphore.tryAcquire(lockTimeout,lockTimeoutUnit);
	}

	
	
	
	
	
}
