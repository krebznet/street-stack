package com.dunkware.trade.service.stream.server.session.worker.protocol;

import com.dunkware.common.util.dtime.DTime;

public class StreamSessionWorkerStopResp {

	private String code; 
	private String error; 
	private DTime stoppingTime; 
	private DTime stopTime;
	
	public StreamSessionWorkerStopResp() { 
		
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
	public DTime getStoppingTime() {
		return stoppingTime;
	}
	public void setStoppingTime(DTime stoppingTime) {
		this.stoppingTime = stoppingTime;
	}
	public DTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(DTime stopTime) {
		this.stopTime = stopTime;
	} 
	
	
}
