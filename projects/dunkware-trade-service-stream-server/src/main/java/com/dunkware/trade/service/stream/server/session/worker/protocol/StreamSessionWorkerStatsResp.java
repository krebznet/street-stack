package com.dunkware.trade.service.stream.server.session.worker.protocol;

import com.dunkware.trade.service.stream.server.session.worker.protocol.spec.StreamSessionWorkerStatsSpec;

public class StreamSessionWorkerStatsResp {

	private String code; 
	private String error; 
	private StreamSessionWorkerStatsSpec spec; 
	
	public StreamSessionWorkerStatsResp() { 
		
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

	public StreamSessionWorkerStatsSpec getSpec() {
		return spec;
	}

	public void setSpec(StreamSessionWorkerStatsSpec spec) {
		this.spec = spec;
	}
	
	
	
	
}
