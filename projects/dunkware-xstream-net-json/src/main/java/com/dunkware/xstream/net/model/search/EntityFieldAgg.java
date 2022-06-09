package com.dunkware.xstream.net.model.search;

import com.dunkware.xstream.net.model.spec.EntityField;

public class EntityFieldAgg {
	
	private EntityField field;
	private EntityFieldAggType type; 
	private EntityFieldAggSession session; 
    private EntityFieldAggHistorical historical;
    
    
	public EntityField getField() {
		return field;
	}
	public void setField(EntityField field) {
		this.field = field;
	}
	public EntityFieldAggType getType() {
		return type;
	}
	public void setType(EntityFieldAggType type) {
		this.type = type;
	}
	public EntityFieldAggSession getSession() {
		return session;
	}
	public void setSession(EntityFieldAggSession session) {
		this.session = session;
	}
	public EntityFieldAggHistorical getHistorical() {
		return historical;
	}
	public void setHistorical(EntityFieldAggHistorical historical) {
		this.historical = historical;
	}
    
    

}
