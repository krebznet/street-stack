package com.dunkware.trade.tick.service.protocol.feed;

public class TickFeedStatsResp {

	private TickFeedStats stats;
	private String error; 
	private String code; 


	public TickFeedStats getStats() {
		return stats;
	}

	public void setStats(TickFeedStats stats) {
		this.stats = stats;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	} 
	
	
	
	
}
