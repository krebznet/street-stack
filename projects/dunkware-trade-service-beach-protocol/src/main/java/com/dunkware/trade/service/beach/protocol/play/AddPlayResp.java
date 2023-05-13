package com.dunkware.trade.service.beach.protocol.play;

public class AddPlayResp {

	private boolean ok = true; 
	private String error = null;
	private long playId; 
	
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
	public long getPlayId() {
		return playId;
	}
	public void setPlayId(long playId) {
		this.playId = playId;
	} 
	
	
	
}
