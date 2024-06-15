package com.dunkware.blocks.metrics;

public interface MetricCounter {

	public void increment(); 
	
	public void decrement();
	
	public String getName();
	
	public int getValue();
}
