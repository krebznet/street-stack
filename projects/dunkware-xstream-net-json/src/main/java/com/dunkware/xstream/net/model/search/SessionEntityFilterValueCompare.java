package com.dunkware.xstream.net.model.search;

public class SessionEntityFilterValueCompare {

	private SessionEntityValue value1; 
	private SessionEntityValue value2; 
	private Condition condition;
	
	private SessionEntityFIlterValueCompareFunction function;
	public SessionEntityValue getValue1() {
		return value1;
	}
	public void setValue1(SessionEntityValue value1) {
		this.value1 = value1;
	}
	public SessionEntityValue getValue2() {
		return value2;
	}
	public void setValue2(SessionEntityValue value2) {
		this.value2 = value2;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public SessionEntityFIlterValueCompareFunction getFunction() {
		return function;
	}
	public void setFunction(SessionEntityFIlterValueCompareFunction function) {
		this.function = function;
	} 
	
	
}
