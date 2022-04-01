package com.dunkware.trade.service.stream.server.controller.session.worker.protocol;

import com.dunkware.trade.service.stream.protocol.controller.spec.StreamWorkerTickers;

public class WorkerTickersResp {
	
	private String code; 
	private String error; 
	private StreamWorkerTickers tickers;
	
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
	public StreamWorkerTickers getTickers() {
		return tickers;
	}
	public void setTickers(StreamWorkerTickers tickers) {
		this.tickers = tickers;
	}
	
	

}
