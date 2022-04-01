package com.dunkware.trade.service.stream.server.session.capture.protocol;

public class SessionEventCaptureWorkerStartResp {
	
	private boolean success; 
	private int captureId; 
	private String error;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCaptureId() {
		return captureId;
	}
	public void setCaptureId(int captureId) {
		this.captureId = captureId;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	
	
	

}
