package com.dunkware.trade.tick.model.feed;

public class TickFeedStats {
	
	private int tradeCount; 
	private int quoteCount; 
	private int qps; 
	private int tps;
	private int mps; 
	private int subscriptionRequestCount; 
	private int subscriptionCount;
	private int messageCount;
	public int getTradeCount() {
		return tradeCount;
	}
	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}
	public int getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(int quoteCount) {
		this.quoteCount = quoteCount;
	}
	public int getQps() {
		return qps;
	}
	public void setQps(int qps) {
		this.qps = qps;
	}
	public int getTps() {
		return tps;
	}
	public void setTps(int tps) {
		this.tps = tps;
	}
	
	public int getSubscriptionCount() {
		return subscriptionCount;
	}
	public void setSubscriptionCount(int subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}
	public int getSubscriptionRequestCount() {
		return subscriptionRequestCount;
	}
	public void setSubscriptionRequestCount(int subscriptionRequestCount) {
		this.subscriptionRequestCount = subscriptionRequestCount;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public int getMps() {
		return mps;
	}
	public void setMps(int mps) {
		this.mps = mps;
	}
	
	
	
	
	
	
	

}
