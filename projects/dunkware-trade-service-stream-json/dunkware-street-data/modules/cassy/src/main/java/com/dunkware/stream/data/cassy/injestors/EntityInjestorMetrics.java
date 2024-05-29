package com.dunkware.stream.data.cassy.injestors;

public class EntityInjestorMetrics {
	
	//queue size 
	private int queue;
	// insert count 
	private long count;
	// errors 
	private int errors; 
	// last batch speed
	private double lastSpeed;
	// last batch size; 
	private int lastSize;  
	// last batch write time 
	private String lastTime;
	// max batch insert size 
	private int maxSize; 
	// max batch insert speed 
	private double maxSpeed; 
	
	public int getQueue() {
		return queue;
	}
	public void setQueue(int queue) {
		this.queue = queue;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public double getLastSpeed() {
		return lastSpeed;
	}
	public void setLastSpeed(double lastSpeed) {
		this.lastSpeed = lastSpeed;
	}
	public int getLastSize() {
		return lastSize;
	}
	public void setLastSize(int lastSize) {
		this.lastSize = lastSize;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
