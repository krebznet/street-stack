package com.dunkware.trade.service.stream.server.web.components;

import java.time.LocalDateTime;

import com.dunkware.utils.core.observable.ObservableBean;

public class EntitySessionVarBean extends ObservableBean {
	
	private String identi; 
	private int id; 
	private int updateCount; 
	private String value; 
	private LocalDateTime lastUpdate;
	public String getIdenti() {
		return identi;
	}
	public void setIdenti(String identi) {
		this.identi = identi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUpdateCount() {
		return updateCount;
	}
	public void setUpdateCount(int updateCount) {
		this.updateCount = updateCount;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	} 
	
	
	

}
