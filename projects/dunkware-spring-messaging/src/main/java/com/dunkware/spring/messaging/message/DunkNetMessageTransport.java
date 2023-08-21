package com.dunkware.spring.messaging.message;

import java.util.Map;

/**
 * This is what gets serialized and sent over wire 
 * Message -> serializes into a Message Transport 
 * and MessageTransport can generate a Message 
 * 
 * @author duncankrebs
 *
 */
public class DunkNetMessageTransport {
	
	private Map<String,Object> headers;
	private String payload; 
	private String payloadClass;
	private String senderId;
	
	
	public Map<String, Object> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getPayloadClass() {
		return payloadClass;
	}
	public void setPayloadClass(String payloadClass) {
		this.payloadClass = payloadClass;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	} 
	
	
	
	
	

}
