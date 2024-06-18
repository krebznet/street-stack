package com.dunkware.trade.service.stream.json.worker.stream;

public class StreamSessionWorkerStatsResp {

	private String code; 
	private String error; 
	private StreamSessionWorkerStats spec; 
	
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

	public StreamSessionWorkerStats getSpec() {
		return spec;
	}

	public void setSpec(StreamSessionWorkerStats spec) {
		this.spec = spec;
	}
	
	
	
	
}
