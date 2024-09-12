package com.dunkware.trade.service.stream.serverd.server.web.components;

import com.dunkware.utils.core.observable.ObservableBean;

public class EntitySessionVarBean extends ObservableBean {
	
	private String ident; 
	private int id; 
	private int updateCount; 
	private String value; 
	private String lastUpdate;
	
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
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	
	

}
