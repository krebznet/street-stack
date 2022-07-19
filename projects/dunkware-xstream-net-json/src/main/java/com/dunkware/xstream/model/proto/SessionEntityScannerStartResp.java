package com.dunkware.xstream.model.proto;

public class SessionEntityScannerStartResp {
	
	private String scannerId; 
	private String streamidentifier; 
	private boolean success; 
	private String error;
	private String data;
	public String getScannerId() {
		return scannerId;
	}
	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	}
	public String getStreamidentifier() {
		return streamidentifier;
	}
	public void setStreamidentifier(String streamidentifier) {
		this.streamidentifier = streamidentifier;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	} 
	
	

}
