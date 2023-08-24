package com.dunkware.spring.cluster.core.controllers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.core.request.DunkNetChannelRequest;
import com.dunkware.spring.cluster.core.request.DunkNetServiceRequest;

public class DunkNetState {
	
	private Map<String,DunkNetChannel> channels = new ConcurrentHashMap<String,DunkNetChannel>();
	private Map<String,DunkNetServiceRequest> pendingServiceRequests = new ConcurrentHashMap<String,DunkNetServiceRequest>();
	private Map<String,DunkNetChannelRequest> pendingChannelRequests = new ConcurrentHashMap<String, DunkNetChannelRequest>();
	
	public Map<String, DunkNetChannel> getChannels() {
		return channels;
	}
	public void setChannels(Map<String, DunkNetChannel> channels) {
		this.channels = channels;
	}
	public Map<String, DunkNetServiceRequest> getPendingServiceRequests() {
		return pendingServiceRequests;
	}
	public void setPendingServiceRequests(Map<String, DunkNetServiceRequest> pendingServiceRequests) {
		this.pendingServiceRequests = pendingServiceRequests;
	}
	public Map<String, DunkNetChannelRequest> getPendingChannelRequests() {
		return pendingChannelRequests;
	}
	public void setPendingChannelRequests(Map<String, DunkNetChannelRequest> pendingChannelRequests) {
		this.pendingChannelRequests = pendingChannelRequests;
	}
	
	public DunkNetServiceRequest removePendingServiceRequest(String reqId) { 
		DunkNetServiceRequest req = pendingServiceRequests.remove(reqId);
		return req;
	}
	
	public DunkNetChannelRequest removePendingChannelRequest(String reqId) { 
		DunkNetChannelRequest req = pendingChannelRequests.remove(reqId);
		return req;
	}
	
	public DunkNetChannel getChannel(String channelId) { 
		return channels.get(channelId);
	}
	
	public void addChannel(DunkNetChannel channel) { 
		this.channels.put(channel.getChannelId(), channel);
	}
	
	public void removeChannel(String channelId) { 
		channels.remove(channelId);
	}
	
	
	
	

}
