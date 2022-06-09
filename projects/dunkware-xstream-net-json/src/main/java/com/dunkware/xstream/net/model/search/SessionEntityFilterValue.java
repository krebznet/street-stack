package com.dunkware.xstream.net.model.search;

public class SessionEntityFilterValue {

	private SessionEntityValue value;
	private Condition condition;
	
	public SessionEntityValue getValue() {
		return value;
	}
	public void setValue(SessionEntityValue value) {
		this.value = value;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
}
