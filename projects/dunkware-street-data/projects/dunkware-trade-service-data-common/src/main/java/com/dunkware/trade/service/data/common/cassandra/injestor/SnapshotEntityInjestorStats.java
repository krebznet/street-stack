package com.dunkware.trade.service.data.common.cassandra.injestor;

public class SnapshotEntityInjestorStats {
	
	private int errors; 
	private int queue; 
	private int inserts;
	private int ils;
	
	
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	public int getQueue() {
		return queue;
	}
	public void setQueue(int queue) {
		this.queue = queue;
	}
	public int getInserts() {
		return inserts;
	}
	public void setInserts(int inserts) {
		this.inserts = inserts;
	}
	public int getIls() {
		return ils;
	}
	public void setIls(int ils) {
		this.ils = ils;
	}
	
	
	
	
	
	
	
	

}
