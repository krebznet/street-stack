package com.dunkware.xstream.model.proto;

public class SessionEntityScannerUpdateResp {

	private String streamIdentifier; 
	private String scannerId; 
	private boolean success; 
	private String data; 
	private String error;
	
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public String getScannerId() {
		return scannerId;
	}
	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	
	
	
       
}
