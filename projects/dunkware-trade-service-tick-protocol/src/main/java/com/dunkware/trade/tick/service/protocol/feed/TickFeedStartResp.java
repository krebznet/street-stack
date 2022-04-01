package com.dunkware.trade.tick.service.protocol.feed;

public class TickFeedStartResp {

	private String code; 
	private String error; 
	private String id;
	
	private String brokers;
	private String topic;
	
	public TickFeedStartResp() { 
		
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrokers() {
		return brokers;
	}
	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	} 
	
	
	
	
}
