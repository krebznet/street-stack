package com.dunkware.trade.tick.service.protocol.service.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.trade.tick.model.feed.TickFeedStatsSpec;
import com.dunkware.trade.tick.model.provider.TickProviderStatsSpec;

public class TickServiceStatusSpec {
	
	private boolean available; 
	private List<TickProviderStatsSpec> providers = new ArrayList<TickProviderStatsSpec>();
	private List<TickFeedStatsSpec> feeds = new ArrayList<TickFeedStatsSpec>();
	private String error;
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public List<TickProviderStatsSpec> getProviders() {
		return providers;
	}
	public void setProviders(List<TickProviderStatsSpec> providers) {
		this.providers = providers;
	}
	public List<TickFeedStatsSpec> getFeeds() {
		return feeds;
	}
	public void setFeeds(List<TickFeedStatsSpec> feeds) {
		this.feeds = feeds;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	
	
	
}
