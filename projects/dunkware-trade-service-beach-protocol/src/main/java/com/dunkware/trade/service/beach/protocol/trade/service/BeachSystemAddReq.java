package com.dunkware.trade.service.beach.protocol.trade.service;

import com.dunkware.trade.sdk.core.model.system.SystemType;

public class BeachSystemAddReq {
	
	private int accountId; 
	private SystemType systemType; 
	private String name;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public SystemType getSystemType() {
		return systemType;
	}
	public void setSystemType(SystemType systemType) {
		this.systemType = systemType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	

}
