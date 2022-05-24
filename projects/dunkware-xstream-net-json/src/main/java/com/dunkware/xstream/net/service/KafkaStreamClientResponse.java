package com.dunkware.xstream.net.service;

public class KafkaStreamClientResponse {
	
	private boolean error = false; 
	private String exception = null; 
	private String kafkaBrokers; 
	private String clientMessageTopic; 
	private String serverMessageTopic;
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getKafkaBrokers() {
		return kafkaBrokers;
	}
	public void setKafkaBrokers(String kafkaBrokers) {
		this.kafkaBrokers = kafkaBrokers;
	}
	public String getClientMessageTopic() {
		return clientMessageTopic;
	}
	public void setClientMessageTopic(String clientMessageTopic) {
		this.clientMessageTopic = clientMessageTopic;
	}
	public String getServerMessageTopic() {
		return serverMessageTopic;
	}
	public void setServerMessageTopic(String serverMessageTopic) {
		this.serverMessageTopic = serverMessageTopic;
	} 
	

}
