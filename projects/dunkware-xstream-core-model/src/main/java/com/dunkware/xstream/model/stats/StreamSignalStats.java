package com.dunkware.xstream.model.stats;

import java.time.LocalDateTime;

public class StreamSignalStats {
	
	private int signalId;
	private String signalIdent;
	private String varIDent;
	private LocalDateTime signalTime; 
	private String streamIdent;
	
	public int getSignalId() {
		return signalId;
	}
	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}
	public String getSignalIdent() {
		return signalIdent;
	}
	public void setSignalIdent(String signalIdent) {
		this.signalIdent = signalIdent;
	}
	public String getVarIDent() {
		return varIDent;
	}
	public void setVarIDent(String varIDent) {
		this.varIDent = varIDent;
	}
	public LocalDateTime getSignalTime() {
		return signalTime;
	}
	public void setSignalTime(LocalDateTime signalTime) {
		this.signalTime = signalTime;
	}
	public String getStreamIdent() {
		return streamIdent;
	}
	public void setStreamIdent(String streamIdent) {
		this.streamIdent = streamIdent;
	} 
	
	

}
