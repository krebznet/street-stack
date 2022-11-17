package com.dunkware.xstream.model.filter;

import com.dunkware.xstream.model.util.Condition;
import com.dunkware.xstream.model.value.SessionEntityValue;

public class SessionEntityFilterValueCompare {

	private SessionEntityValue value1; 
	private SessionEntityValue value2; 
	private Condition condition;
	
	private SessionEntityFilterValueCompareFunctionHolder function;
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
	public SessionEntityFilterValueCompareFunctionHolder getFunction() {
		return function;
	}
	public void setFunction(SessionEntityFilterValueCompareFunctionHolder function) {
		this.function = function;
	}
	
	
	
}
