package com.dunkware.common.kafka.admin.model;

public class DKafkaNewTopicResult {

	private double time; 
	private String topic; 
	private boolean exception; 
	private String cause;
	private boolean timeout = false; 
	
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public boolean isException() {
		return exception;
	}
	public void setException(boolean exception) {
		this.exception = exception;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public boolean isTimeout() {
		return timeout;
	}
	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	} 
	
	
	
}
