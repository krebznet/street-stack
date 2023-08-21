package com.dunkware.trade.net.service.streamstats.client;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import com.dunkware.spring.messaging.DunkNet;

public class StreamStatsClientInput {

	private DunkNet dunkNet;
	private LocalDate date;
	private TimeUnit timeoutUnit = TimeUnit.SECONDS; 
	private long timeout = 30;
	
	public DunkNet getDunkNet() {
		return dunkNet;
	}
	public void setDunkNet(DunkNet dunkNet) {
		this.dunkNet = dunkNet;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public TimeUnit getTimeoutUnit() {
		return timeoutUnit;
	}
	public void setTimeoutUnit(TimeUnit timeoutUnit) {
		this.timeoutUnit = timeoutUnit;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	
	

	
	
	
}
