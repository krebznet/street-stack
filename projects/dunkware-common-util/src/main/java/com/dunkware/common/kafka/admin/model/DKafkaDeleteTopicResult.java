package com.dunkware.common.kafka.admin.model;

public class DKafkaDeleteTopicResult {

	private String topic; 
	private boolean exception; 
	private String cause;
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
	
	
}
