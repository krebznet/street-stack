package com.dunkware.spring.channel.protocol;

public class CreateChannelRequest {
	
	private String requestId;
	private String callbackTopic; 
	private String type;
	
	public String getCallbackTopic() {
		return callbackTopic;
	}
	public void setCallbackTopic(String callbackTopic) {
		this.callbackTopic = callbackTopic;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	
	

}
