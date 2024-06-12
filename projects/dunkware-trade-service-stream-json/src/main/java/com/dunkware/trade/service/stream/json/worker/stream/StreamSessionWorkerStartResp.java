package com.dunkware.trade.service.stream.json.worker.stream;

import java.time.LocalTime;

public class StreamSessionWorkerStartResp {

	private String code;
	private String error;
	private LocalTime startingTime;
	private LocalTime startTime;

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

	public LocalTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(LocalTime startingTime) {
		this.startingTime = startingTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

}
