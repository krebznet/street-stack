package com.dunkware.trade.service.beach.server.broker;

import com.dunkware.common.util.observable.ObservableBean;

public class BeachBrokerBean extends ObservableBean {
	
	private String status; 
	private String name;
	private String summary;
	private int accounts; 
	private long id;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getAccounts() {
		return accounts;
	}
	public void setAccounts(int accounts) {
		this.accounts = accounts;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	} 
	
	

}
