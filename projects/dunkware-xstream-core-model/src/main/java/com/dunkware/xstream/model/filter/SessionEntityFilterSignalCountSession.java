package com.dunkware.xstream.model.filter;

import com.dunkware.xstream.model.util.Condition;
import com.dunkware.xstream.model.value.EntitySignalCountSession;

public class SessionEntityFilterSignalCountSession {

	private EntitySignalCountSession signalCount; 
	private Condition condition;
	
	public EntitySignalCountSession getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(EntitySignalCountSession signalCount) {
		this.signalCount = signalCount;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	} 
	
	
}
