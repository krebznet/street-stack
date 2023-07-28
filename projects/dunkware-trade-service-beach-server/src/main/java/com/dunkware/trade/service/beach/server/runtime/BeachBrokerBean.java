package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.databean.DataBean;
import com.dunkware.common.util.helpers.DRandom;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;

public class BeachBrokerBean extends DataBean {

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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
	
	
	
	
}
