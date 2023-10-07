package com.dunkware.trade.tick.model.provider;

import com.dunkware.common.util.dtime.DDateTime;

public class TickProviderStatsSpec {
	
 
	private String name; 
	private int subscriptionCount; 
	private int quoteCount; 
	private int secondAggCount; 
	private int messageCount; 
	private DDateTime startTime; 
	private int invalidSubscriptionCount; 
	private int mps; 
	private int qps; 
	private int aps; 
	private int messageQueueSize; 
	private int validatedSubscriptions; 
	private String lastStreamMessage; 
	private String lastSnapshotMessage; 
	
	private TickProviderState state;
	
	public TickProviderStatsSpec() { 
		
		
	}

	
	public int getSubscriptionCount() {
		return subscriptionCount;
	}

	public void setSubscriptionCount(int subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuoteCount() {
		return quoteCount;
	}

	public void setQuoteCount(int quoteCount) {
		this.quoteCount = quoteCount;
	}

	public int getSecondAggCount() {
		return secondAggCount;
	}

	public void setSecondAggCount(int secondAggCount) {
		this.secondAggCount = secondAggCount;
	}

	public int getInvalidSubscriptionCount() {
		return invalidSubscriptionCount;
	}

	public void setInvalidSubscriptionCount(int invalidSubscriptionCount) {
		this.invalidSubscriptionCount = invalidSubscriptionCount;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	public DDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DDateTime startTime) {
		this.startTime = startTime;
	}

	public int getMps() {
		return mps;
	}

	public void setMps(int mps) {
		this.mps = mps;
	}

	public TickProviderState getState() {
		return state;
	}

	public void setState(TickProviderState state) {
		this.state = state;
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


	public int getValidatedSubscriptions() {
		return validatedSubscriptions;
	}


	public void setValidatedSubscriptions(int validatedSubscriptions) {
		this.validatedSubscriptions = validatedSubscriptions;
	}


	public String getLastStreamMessage() {
		return lastStreamMessage;
	}


	public void setLastStreamMessage(String lastStreamMessage) {
		this.lastStreamMessage = lastStreamMessage;
	}


	public String getLastSnapshotMessage() {
		return lastSnapshotMessage;
	}


	public void setLastSnapshotMessage(String lastSnapshotMessage) {
		this.lastSnapshotMessage = lastSnapshotMessage;
	}
	
	

	
	
	
	
	
	
	
	

}
