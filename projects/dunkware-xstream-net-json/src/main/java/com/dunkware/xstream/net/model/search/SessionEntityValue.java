package com.dunkware.xstream.net.model.search;

public class SessionEntityValue {

	private SessionEntityValueType type;
	private EntityFieldAggSession aggSession; 
	private EntityFieldAggHistorical aggHistorical;
	private EntitySignalCountSession signalCountSession; 
	private EntitySignalCountHistorical signalCountHistorical;
	public SessionEntityValueType getType() {
		return type;
	}
	public void setType(SessionEntityValueType type) {
		this.type = type;
	}
	public EntityFieldAggSession getAggSession() {
		return aggSession;
	}
	public void setAggSession(EntityFieldAggSession aggSession) {
		this.aggSession = aggSession;
	}
	public EntityFieldAggHistorical getAggHistorical() {
		return aggHistorical;
	}
	public void setAggHistorical(EntityFieldAggHistorical aggHistorical) {
		this.aggHistorical = aggHistorical;
	}
	public EntitySignalCountSession getSignalCountSession() {
		return signalCountSession;
	}
	public void setSignalCountSession(EntitySignalCountSession signalCountSession) {
		this.signalCountSession = signalCountSession;
	}
	public EntitySignalCountHistorical getSignalCountHistorical() {
		return signalCountHistorical;
	}
	public void setSignalCountHistorical(EntitySignalCountHistorical signalCountHistorical) {
		this.signalCountHistorical = signalCountHistorical;
	} 
	
	
}
