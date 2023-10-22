package com.dunkware.trade.service.stream.server.blueprint;

import java.time.LocalTime;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamBlueprintSignalBean extends ObservableBean {
	
	private String name;
	private String description;
	private String created; 
	private String status; 
	private String group;
	private long id; 
	
	private int sessionSignalCount;
	private LocalTime sessionSignalLastTime;
	private String sessionSignalLastEntity; 				
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String active) {
		this.status = active;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
