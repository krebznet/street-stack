package com.dunkware.trade.service.stream.server.session.xstream;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.trade.tick.model.feed.TickFeedSpec;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

public class StreamSessionTickFeedExtType extends XStreamExtensionType {
	
	private TickFeedSpec feedSpec;
	private String serviceEndpoint; 
	private Map<Integer,Integer> dataTicks = new HashMap<Integer,Integer>();

	public TickFeedSpec getFeedSpec() {
		return feedSpec;
	}

	public void setFeedSpec(TickFeedSpec feedSpec) {
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
