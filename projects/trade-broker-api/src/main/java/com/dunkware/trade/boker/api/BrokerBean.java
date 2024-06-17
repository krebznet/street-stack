package com.dunkware.trade.boker.api;

public class BrokerBean {
	
	private String connectTime; 
	private String disconnectCount; 
	private String disconnectTime; 
	private String status; 
	private int connectCounts; 
	private int connectAttempts;
	
	public String getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(String connectTime) {
		this.connectTime = connectTime;
	}
	public String getDisconnectCount() {
		return disconnectCount;
	}
	public void setDisconnectCount(String disconnectCount) {
		this.disconnectCount = disconnectCount;
	}
	public String getDisconnectTime() {
		return disconnectTime;
	}
	public void setDisconnectTime(String disconnectTime) {
		this.disconnectTime = disconnectTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getConnectCounts() {
		return connectCounts;
	}
	public void setConnectCounts(int connectCounts) {
		this.connectCounts = connectCounts;
	}
	public int getConnectAttempts() {
		return connectAttempts;
	}
	public void setConnectAttempts(int connectAttempts) {
		this.connectAttempts = connectAttempts;
	} 
	

}
