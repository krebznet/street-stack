package com.dunkware.xstream.model.util;

public class ConditionNumeric {
	
	private Number value; 
	private ConditionNumericOperator operator;
	
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public ConditionNumericOperator getOperator() {
		return operator;
	}
	public void setOperator(ConditionNumericOperator operator) {
		this.operator = operator;
	} 
	
	

}
