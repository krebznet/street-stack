package com.dunkware.trade.service.stream.json.controller.session;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;

public class StreamSessionNodeStats {
	
	private String node; 
	private StreamSessionWorkerStats worker; 
	private DDateTime startingTime;
	private DDateTime startedTime;
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public StreamSessionWorkerStats getWorker() {
		return worker;
	}
	public void setWorker(StreamSessionWorkerStats worker) {
		this.worker = worker;
	}
	public DDateTime getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(DDateTime startingTime) {
		this.startingTime = startingTime;
	}
	public DDateTime getStartedTime() {
		return startedTime;
	}
	public void setStartedTime(DDateTime startedTime) {
		this.startedTime = startedTime;
	}
	
	

}
