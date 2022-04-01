package com.dunkware.trade.tick.model.provider;

public class TickProviderStatsSpec {
	
	private TickProviderStatus status; 
	private int subscriptionCount; 
	
	public TickProviderStatsSpec() { 
		
		
	}

	public TickProviderStatus getStatus() {
		return status;
	}

	public void setStatus(TickProviderStatus status) {
		this.status = status;
	}

	public int getSubscriptionCount() {
		return subscriptionCount;
	}

	public void setSubscriptionCount(int subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}
	
	

}
