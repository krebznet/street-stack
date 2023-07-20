package com.dunkware.common.util.events;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.dunkware.common.util.executor.DExecutor;

public class DEventTree {
	
	public static final long LOCK_TIMEOUT = 30; 
	public static final TimeUnit LOCK_TIMEOUT_UNIT = TimeUnit.SECONDS;
	
	public static DEventTree newInstance(DExecutor executor)  { 
		return new DEventTree(executor);
	}

	private DEventNode rootNode;
	private DExecutor executor;
	private long lockTimeout; 
	private TimeUnit lockTimeoutUnit;
	
	private DEventTree(DExecutor executor) { 
		rootNode = new DEventNode(null, "/",this);
		this.executor = executor;
		this.lockTimeout = LOCK_TIMEOUT;
		this.lockTimeoutUnit = LOCK_TIMEOUT_UNIT;
	}
	
	public DEventNode getRoot() { 
		return rootNode;
	}
	
	public DExecutor getExecutor() { 
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
