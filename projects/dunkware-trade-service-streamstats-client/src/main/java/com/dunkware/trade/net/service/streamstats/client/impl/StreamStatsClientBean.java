package com.dunkware.trade.net.service.streamstats.client.impl;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class StreamStatsClientBean {

	private String requestTopic; 
	private String responseTopic; 
	private String clientId; 
	private String groupId; 
	private LocalDate date;
	private String brokers; 
	private TimeUnit timeoutUnit = TimeUnit.SECONDS; 
	private long timeout = 30;
	
	public String getRequestTopic() {
		return requestTopic;
	}
	public void setRequestTopic(String requestTopic) {
		this.requestTopic = requestTopic;
	}
	public String getResponseTopic() {
		return responseTopic;
	}
	public void setResponseTopic(String responseTopic) {
		this.responseTopic = responseTopic;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getBrokers() {
		return brokers;
	}
	public void setBrokers(String brokers) {
		this.brokers = brokers;
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
