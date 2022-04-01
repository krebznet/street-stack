package com.dunkware.trade.service.stream.protocol.controller;

import com.dunkware.trade.service.stream.protocol.controller.spec.StreamStatsSpec;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StreamStatsResp {

	private String code; 
	@JsonInclude(Include.NON_NULL)
	private String error; 
	@JsonInclude(Include.NON_NULL)
	private StreamStatsSpec stats;

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

	public StreamStatsSpec getStats() {
		return stats;
	}

	public void setStats(StreamStatsSpec stats) {
		this.stats = stats;
	}

	
	
	
}
