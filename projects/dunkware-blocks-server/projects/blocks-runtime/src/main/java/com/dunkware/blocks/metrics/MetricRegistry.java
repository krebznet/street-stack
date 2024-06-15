package com.dunkware.blocks.metrics;

import java.util.List;

import com.dunkware.blocks.model.MetricDash;

public interface MetricRegistry {

	public String getName();
	
	public List<MetricSet> getSets();
	
	public MetricDash getDashModel();
	
	public Object getData();

}
