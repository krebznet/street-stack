package com.dunkware.trade.service.stream.json.worker.stream;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StreamSessionWorkerStopResp {

	private String code; 
	private String error; 
	private LocalTime stoppingTime; 
	private LocalTime stopTime;
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
	public LocalTime getStoppingTime() {
		return stoppingTime;
	}
	public void setStoppingTime(LocalTime stoppingTime) {
		this.stoppingTime = stoppingTime;
	}
	public LocalTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	}

	public List<String> getStopProblems() {
		return stopProblems;
	}

	public void setStopProblems(List<String> stopProblems) {
		this.stopProblems = stopProblems;
	} 
	
	
	
	
}
