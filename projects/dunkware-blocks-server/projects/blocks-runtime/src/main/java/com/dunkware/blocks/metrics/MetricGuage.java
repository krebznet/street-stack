package com.dunkware.blocks.metrics;

public interface MetricGuage {

	public void setValue(Number number);
	
	public Number getValue();
	
	public String getName();
}
