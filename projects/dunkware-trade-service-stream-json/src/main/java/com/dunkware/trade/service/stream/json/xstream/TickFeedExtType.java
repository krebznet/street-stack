package com.dunkware.trade.service.stream.json.xstream;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.trade.tick.model.consumer.TickConsumerSpec;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class TickFeedExtType extends XStreamExtensionType {
	
	private TickConsumerSpec feedSpec;
	private String serviceEndpoint; 
	private Map<Integer,Integer> dataTicks = new HashMap<Integer,Integer>();

	public TickConsumerSpec getFeedSpec() {
		return feedSpec;
	}

	public void setFeedSpec(TickConsumerSpec feedSpec) {
		this.feedSpec = feedSpec;
	}

	public String getServiceEndpoint() {
		return serviceEndpoint;
	}

	public void setServiceEndpoint(String serviceEndpoint) {
		this.serviceEndpoint = serviceEndpoint;
	}

	public Map<Integer, Integer> getDataTicks() {
		return dataTicks;
	}

	public void setDataTicks(Map<Integer, Integer> dataTicks) {
		this.dataTicks = dataTicks;
	}

	
	
	

}
