package com.dunkware.xstream.net.model.search;

import com.dunkware.xstream.net.model.spec.EntityField;

public class SessionEntityValue {

	private SessionEntityValueType type;
	private EntityFieldAggSession aggSession; 
	private EntityFieldAggHistorical aggHistorical;
	private EntitySignalCountSession signalCountSession; 
	private EntitySignalCountHistorical signalCountHistorical;
	private EntityField field; 
	
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
	public EntityField getField() {
		return field;
	}
	public void setField(EntityField field) {
		this.field = field;
	} 
	
	
	
	
}
