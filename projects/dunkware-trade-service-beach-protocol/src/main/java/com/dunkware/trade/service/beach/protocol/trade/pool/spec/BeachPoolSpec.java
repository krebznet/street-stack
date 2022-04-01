package com.dunkware.trade.service.beach.protocol.trade.pool.spec;

import com.dunkware.trade.service.beach.protocol.trade.pool.BeachPoolStatus;

public class BeachPoolSpec {

	private String identifier; 
	private BeachPoolStatus status;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public BeachPoolStatus getStatus() {
		return status;
	}
	public void setStatus(BeachPoolStatus status) {
		this.status = status;
	}
	
	
}
