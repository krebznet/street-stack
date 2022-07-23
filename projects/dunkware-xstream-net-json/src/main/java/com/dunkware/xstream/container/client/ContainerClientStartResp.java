package com.dunkware.xstream.container.client;

public class ContainerClientStartResp {

	private boolean error = false; 
	private String exception; 
	private String brokers; 
	private String clientTopic; 
	private String serverTopic;
	
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
	public String getBrokers() {
		return brokers;
	}
	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}
	public String getClientTopic() {
		return clientTopic;
	}
	public void setClientTopic(String clientTopic) {
		this.clientTopic = clientTopic;
	}
	public String getServerTopic() {
		return serverTopic;
	}
	public void setServerTopic(String serverTopic) {
		this.serverTopic = serverTopic;
	} 
	
	
}
