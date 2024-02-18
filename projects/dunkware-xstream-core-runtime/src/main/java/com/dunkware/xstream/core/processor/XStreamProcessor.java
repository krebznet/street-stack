package com.dunkware.xstream.core.processor;

import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.model.processor.XStreamProcessorStats;

public class XStreamProcessor {

	private XStream stream; 
	private XStreamProcessorStatsWrapper statsWrapper; 
	
	public void start(XStream stream) throws Exception {  
		this.stream = stream;
		statsWrapper = XStreamProcessorStatsWrapper.newInstnace();
	}
	
	
	public XStreamProcessorStats getStats() { 
		return statsWrapper.getStats();
	}
	
	
	

}
