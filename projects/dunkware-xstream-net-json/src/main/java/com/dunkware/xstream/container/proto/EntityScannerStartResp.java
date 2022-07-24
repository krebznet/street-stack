package com.dunkware.xstream.container.proto;

public class EntityScannerStartResp {
	
	private String scannerId; 
	private boolean success = true; 
	private String exception;
	
	
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
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	} 
	
	

}
