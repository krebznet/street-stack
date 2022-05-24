package com.dunkware.xstream.net.service;

public class KafkaStreamClientRequest {

	private String stream; 
	private String clientIdentifier;
	
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getClientIdentifier() {
		return clientIdentifier;
	}
	public void setClientIdentifier(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	} 
	
	
}
