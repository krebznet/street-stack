package com.dunkware.blocks.metrics.impl;

import com.dunkware.blocks.metrics.MetricGuage;

public class MetricGuageImpl implements MetricGuage {

	private Number value; 
	private String name; 
	
	public MetricGuageImpl(String name) { 
		this.name = name; 
	}
	
	@Override
	public void setValue(Number number) {
		this.value = number;
	}

	@Override
	public Number getValue() {
		return value; 
	}

	@Override
	public String getName() {
		return name; 
	}

	
	
}
