package com.dunkware.trade.service.data.common.cassandra.injestor;

import com.datastax.oss.driver.api.core.CqlSession;

public class SnapshotEntityInjestorConfig {
	
	private CqlSession session; 
	private int threads; 
	private int batchSize; 
	
	public SnapshotEntityInjestorConfig(CqlSession session, int threads, int batchSize) { 
		this.session = session; 
		this.threads = threads;
		this.batchSize = batchSize; 
	}


	public CqlSession getSession() {
		return session;
	}


	public void setSession(CqlSession session) {
		this.session = session;
	}


	public int getThreads() {
		return threads;
	}


	public void setThreads(int threads) {
		this.threads = threads;
	}


	public int getBatchSize() {
		return batchSize;
	}


	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	
	
	
	
	
	

}
