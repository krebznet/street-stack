package com.dunkware.trade.tick.service.protocol.service.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class FeedServiceStats {
	
	private int subscriptionCount; 
	private int feedCount; 
	private int messageCount; 
	private int quoteCount; 
	private int aggCount; 
	private int invalidSymbolCount; 
	private int mps; 
	private int qps; 
	private int aps; 
	private int messageQueueSize;
	private DDateTime startTime;
	private FeedServiceState state;
	private int validatedSubscriptionCount;
	private int invalidatedSubscriptionCount;
	
	public int getSubscriptionCount() {
		return subscriptionCount;
	}
	public void setSubscriptionCount(int subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}
	public int getFeedCount() {
		return feedCount;
	}
	public void setFeedCount(int feedCount) {
		this.feedCount = feedCount;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public int getQuoteCount() {
		return quoteCount;
	}
	public void setQuoteCount(int quoteCount) {
		this.quoteCount = quoteCount;
	}
	public int getAggCount() {
		return aggCount;
	}
	public void setAggCount(int aggCount) {
		this.aggCount = aggCount;
	}
	public int getInvalidSymbolCount() {
		return invalidSymbolCount;
	}
	public void setInvalidSymbolCount(int invalidSymbolCount) {
		this.invalidSymbolCount = invalidSymbolCount;
	}
	public int getMps() {
		return mps;
	}
	public void setMps(int mps) {
		this.mps = mps;
	}
	public int getQps() {
		return qps;
	}
	public void setQps(int qps) {
		this.qps = qps;
	}
	public int getAps() {
		return aps;
	}
	public void setAps(int aps) {
		this.aps = aps;
	}
	public int getMessageQueueSize() {
		return messageQueueSize;
	}
	public void setMessageQueueSize(int messageQueueSize) {
		this.messageQueueSize = messageQueueSize;
	}
	public DDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DDateTime startTime) {
		this.startTime = startTime;
	}
	public FeedServiceState getState() {
		return state;
	}
	public void setState(FeedServiceState state) {
		this.state = state;
	}
	public int getValidatedSubscriptionCount() {
		return validatedSubscriptionCount;
	}
	public void setValidatedSubscriptionCount(int validatedSubscriptionCount) {
		this.validatedSubscriptionCount = validatedSubscriptionCount;
	}
	public int getInvalidatedSubscriptionCount() {
		return invalidatedSubscriptionCount;
	}
	public void setInvalidatedSubscriptionCount(int invalidatedSubscriptionCount) {
		this.invalidatedSubscriptionCount = invalidatedSubscriptionCount;
	}
	
	
	
	
	
	
	

}
