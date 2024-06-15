package com.dunkware.blocks.metrics.impl;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.blocks.metrics.MetricCounter;

public class MetricCounterImpl implements MetricCounter {

	
	private AtomicInteger value = new AtomicInteger(0);
	private String name;
	
	public MetricCounterImpl() { 
		
	}
	
	@Override
	public int getValue() {
		return value.get();
	}

	@Override
	public void increment() {
		value.incrementAndGet();
		
	}

	@Override
	public void decrement() {
		value.decrementAndGet();
		
	}

	@Override
	public String getName() {
		return name; 
	}

	
}
