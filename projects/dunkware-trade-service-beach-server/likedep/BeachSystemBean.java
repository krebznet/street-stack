package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachSystemBean extends ObservableBean {

	private String accountIdent;
	private long accountId; 
	private long systemId; 
	private String systemIdent; 
	private double allocatedCapital;
	private String status; 
	private String exception; 
	
	public String getAccountIdent() {
		return accountIdent;
	}
	public void setAccountIdent(String accountIdent) {
		this.accountIdent = accountIdent;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getSystemId() {
		return systemId;
	}
	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}
	public String getSystemIdent() {
		return systemIdent;
	}
	public void setSystemIdent(String systemIdent) {
		this.systemIdent = systemIdent;
	}
	public double getAllocatedCapital() {
		return allocatedCapital;
	}
	public void setAllocatedCapital(double allocatedCapital) {
		this.allocatedCapital = allocatedCapital;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	} 
	
	
	
	
	
	
	
}
