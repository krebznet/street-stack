package com.dunkware.trade.service.stream.json.controller;

import com.dunkware.trade.service.stream.json.controller.spec.StreamControllerStats;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamStatsResp {

	private String code; 
	@JsonInclude(Include.NON_NULL)
	private String error; 
	@JsonInclude(Include.NON_NULL)
	private StreamControllerStats stats;

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

	public StreamControllerStats getStats() {
		return stats;
	}

	public void setStats(StreamControllerStats stats) {
		this.stats = stats;
	}

	
	
	
}
