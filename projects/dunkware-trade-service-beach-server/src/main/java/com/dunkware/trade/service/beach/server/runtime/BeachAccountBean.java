package com.dunkware.trade.service.beach.server.runtime;

import com.dunkware.common.util.databean.DataBean;

public class BeachAccountBean extends DataBean {

	private String name; 
	private String broker;
	private long id; 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	} 
	
	
	
	
	
}
