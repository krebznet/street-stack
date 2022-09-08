package com.dunkware.xstream.container.proto;

import com.dunkware.common.util.grid.GridOptions;

public class EntityScannerStartResp {
	
	private String scannerId; 
	private boolean success = true; 
	private String exception;
	private GridOptions options;
	
	
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
	public GridOptions getOptions() {
		return options;
	}
	public void setOptions(GridOptions options) {
		this.options = options;
	} 
	
	
	
	

}
