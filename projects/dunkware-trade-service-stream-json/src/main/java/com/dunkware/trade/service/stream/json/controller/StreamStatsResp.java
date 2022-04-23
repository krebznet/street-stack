package com.dunkware.trade.service.stream.json.controller;

import com.dunkware.trade.service.stream.json.controller.spec.StreamStats;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamStatsResp {

	private String code; 
	@JsonInclude(Include.NON_NULL)
	private String error; 
	@JsonInclude(Include.NON_NULL)
	private StreamStats stats;

	public StreamStatsResp() { 
		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public StreamStats getStats() {
		return stats;
	}

	public void setStats(StreamStats stats) {
		this.stats = stats;
	}

	
	
	
}
