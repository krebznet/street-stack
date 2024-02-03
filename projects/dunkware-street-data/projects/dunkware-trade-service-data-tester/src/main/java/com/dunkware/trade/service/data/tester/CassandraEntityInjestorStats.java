package com.dunkware.trade.service.data.tester;

public class CassandraEntityInjestorStats {

	private int errors; 
	private int queue; 
	private int inserts;
	
	
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
	
	
	
}
