package com.dunkware.trade.service.web;

public class SessionEntityStreamResponse {
	
	private String socketEndpoint; 
	private int returnCode; 
	private String error;
	
	public String getSocketEndpoint() {
		return socketEndpoint;
	}
	public void setSocketEndpoint(String socketEndpoint) {
		this.socketEndpoint = socketEndpoint;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	

}
