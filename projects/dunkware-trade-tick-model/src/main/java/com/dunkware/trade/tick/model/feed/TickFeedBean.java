package com.dunkware.trade.tick.model.feed;

import com.dunkware.common.util.dtime.DDateTime;

public class TickFeedBean {
	
	private int subscriptionCount;
	private int quoteCount; 
	private int tradeCount; 
	private int snapshotCount; 
	private DDateTime lastUpdate; 
	
	public int getSubscriptionCount() {
		return subscriptionCount;
	}
	public void setSubscriptionCount(int subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}
	public int getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(int quoteCount) {
		this.quoteCount = quoteCount;
	}
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(int snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public DDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(DDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	

}
