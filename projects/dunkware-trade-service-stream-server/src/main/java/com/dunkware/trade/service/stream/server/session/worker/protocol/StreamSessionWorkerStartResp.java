package com.dunkware.trade.service.stream.server.session.worker.protocol;

import com.dunkware.common.util.dtime.DTime;

public class StreamSessionWorkerStartResp {

	private String code; 
	private String error; 
	private DTime startingTime; 
	private DTime startTime; 
	
	public StreamSessionWorkerStartResp() { 
		
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

	public DTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(DTime startingTime) {
		this.startingTime = startingTime;
	}

	public DTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DTime startTime) {
		this.startTime = startTime;
	}

	

	
	
	
	
}
