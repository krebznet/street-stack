package com.dunkware.trade.service.stream.json.worker.stream;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTime;

public class StreamSessionWorkerStopResp {

	private String code; 
	private String error; 
	private DTime stoppingTime; 
	private DTime stopTime;
	private List<String> stopProblems = new ArrayList<String>();
	
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

	public List<String> getStopProblems() {
		return stopProblems;
	}

	public void setStopProblems(List<String> stopProblems) {
		this.stopProblems = stopProblems;
	} 
	
	
	
	
}
