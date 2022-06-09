package com.dunkware.xstream.net.model.search;

public class EntitySignalCount {
	
	private EntitySignalCountType type; 
	private EntitySignalCountSession session; 
	private EntitySignalCountHistorical historical;
	
	public EntitySignalCountType getType() {
		return type;
	}
	public void setType(EntitySignalCountType type) {
		this.type = type;
	}
	public EntitySignalCountSession getSession() {
		return session;
	}
	public void setSession(EntitySignalCountSession session) {
		this.session = session;
	}
	public EntitySignalCountHistorical getHistorical() {
		return historical;
	}
	public void setHistorical(EntitySignalCountHistorical historical) {
		this.historical = historical;
	} 
	
	

}
