package com.dunkware.trade.service.proto;

public class SessionEntityScannerStartResponse {
	
	private String topic; 
	private String broker; 
	private boolean error; 
	private String errorMessage; 
	private int scannerId; 
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getScannerId() {
		return scannerId;
	}
	public void setScannerId(int scannerId) {
		this.scannerId = scannerId;
	}

	
	

}
