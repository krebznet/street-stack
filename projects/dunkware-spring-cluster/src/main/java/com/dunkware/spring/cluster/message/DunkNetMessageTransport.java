package com.dunkware.spring.cluster.message;

import java.util.Map;

import com.dunkware.common.util.uuid.DUUID;

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
	private int type;
	private String channel = null;
	private String messageId = DUUID.randomUUID(5);
	
	
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	} 
	
	
	
	
	
	
	
	

}
