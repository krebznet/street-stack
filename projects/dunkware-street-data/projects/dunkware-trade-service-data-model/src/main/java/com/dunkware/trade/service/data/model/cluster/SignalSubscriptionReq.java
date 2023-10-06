package com.dunkware.trade.service.data.model.cluster;

import java.util.List;

public class SignalSubscriptionReq {

	private String streamIdentifier; 
	private List<Integer> subscriptions;
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public List<Integer> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<Integer> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
}
