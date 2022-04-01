package com.dunkware.trade.tick.service.protocol.feed;

import com.dunkware.trade.tick.model.feed.TickFeedStatsSpec;

public class TickFeedStatsResp {

	private TickFeedStatsSpec spec;
	private String error; 
	private String code; 
	

	public TickFeedStatsSpec getSpec() {
		return spec;
	}

	public void setSpec(TickFeedStatsSpec spec) {
		this.spec = spec;
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
