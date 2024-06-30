package com.dunkware.xstream.core.processor;

import com.dunkware.xstream.model.processor.XStreamProcessorStats;

public class XStreamProcessorStatsWrapper {

	public static XStreamProcessorStatsWrapper newInstnace() {
		return new XStreamProcessorStatsWrapper();
		
	}
	
	private XStreamProcessorStats stats;
	
	private XStreamProcessorStatsWrapper() { 
		stats = new XStreamProcessorStats();
	}
	
	
	public XStreamProcessorStats getStats() { 
		return stats; 
	}
	
	
	
	
}
