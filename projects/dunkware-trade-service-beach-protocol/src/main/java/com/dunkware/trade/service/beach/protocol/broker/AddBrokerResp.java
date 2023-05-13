package com.dunkware.trade.service.beach.protocol.broker;

public class AddBrokerResp {
	
	private boolean ok = true; 
	private String error = null;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	

}
