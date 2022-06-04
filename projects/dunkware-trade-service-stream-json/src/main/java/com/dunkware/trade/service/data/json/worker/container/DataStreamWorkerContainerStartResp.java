package com.dunkware.trade.service.data.json.worker.container;

public class DataStreamWorkerContainerStartResp {
	
	private boolean successfull = true; 
	private String exception = null;
	
	public boolean isSuccessfull() {
		return successfull;
	}
	public void setSuccessfull(boolean successfull) {
		this.successfull = successfull;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	

}
