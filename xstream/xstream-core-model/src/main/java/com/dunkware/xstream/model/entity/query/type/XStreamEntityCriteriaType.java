package com.dunkware.xstream.model.entity.query.type;

public class XStreamEntityCriteriaType {
	
	private XStreamEntityCriteriaKind type;
	private XStreamEntityValueType value1; 
	private XStreamEntityValueType value2;
	private XStreamCriteriaCompareFunc compareFunc; 
	private XStreamOperator operator; 
	private Number operatorValue; 
	
	public XStreamEntityCriteriaKind getType() {
		return type;
	}
	public void setType(XStreamEntityCriteriaKind type) {
		this.type = type;
	}
	public XStreamEntityValueType getValue1() {
		return value1;
	}
	public void setValue1(XStreamEntityValueType value1) {
		this.value1 = value1;
	}
	public XStreamEntityValueType getValue2() {
		return value2;
	}
	public void setValue2(XStreamEntityValueType value2) {
		this.value2 = value2;
	}
	public XStreamCriteriaCompareFunc getCompareFunc() {
		return compareFunc;
	}
	public void setCompareFunc(XStreamCriteriaCompareFunc compareFunc) {
		this.compareFunc = compareFunc;
	}
	public XStreamOperator getOperator() {
		return operator;
	}
	public void setOperator(XStreamOperator operator) {
		this.operator = operator;
	}
	public Number getOperatorValue() {
		return operatorValue;
	}
	public void setOperatorValue(Number operatorValue) {
		this.operatorValue = operatorValue;
	}
	
	
	
	
	
	
	
	
	
	

}
