package com.dunkware.trade.service.stream.server.controller.session.worker.protocol;

import com.dunkware.trade.service.stream.protocol.controller.spec.StreamWorkerMetrics;
import com.dunkware.xstream.model.metrics.XStreamMetrics;

public class WorkerMetricsResp {

	private String code; 
	private String error; 
	private StreamWorkerMetrics stats;
	
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

	public StreamWorkerMetrics getStats() {
		return stats;
	}

	public void setStats(StreamWorkerMetrics stats) {
		this.stats = stats;
	}
	
	

	
	
}
