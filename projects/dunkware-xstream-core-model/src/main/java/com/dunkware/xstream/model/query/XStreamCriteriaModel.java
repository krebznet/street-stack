package com.dunkware.xstream.model.query;

public class XStreamCriteriaModel {
	
	private XStreamEntityCriteriaType type;
	private XStreamEntityValueModel value1; 
	private XStreamEntityValueModel value2;
	private XStreamCriteriaCompareFunc compareFunc; 
	private XStreamOperator operator; 
	private Number operatorValue; 
	
	public XStreamEntityCriteriaType getType() {
		return type;
	}
	public void setType(XStreamEntityCriteriaType type) {
		this.type = type;
	}
	public XStreamEntityValueModel getValue1() {
		return value1;
	}
	public void setValue1(XStreamEntityValueModel value1) {
		this.value1 = value1;
	}
	public XStreamEntityValueModel getValue2() {
		return value2;
	}
	public void setValue2(XStreamEntityValueModel value2) {
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
