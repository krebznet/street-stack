package com.dunkware.xstream.model.query;

public class XStreamCriteriaModel {
	
	private XStreamCriteriaType type;
	private XStreamRowValueModel value1; 
	private XStreamRowValueModel value2;
	private XStreamCriteriaCompareFunc compareFunc; 
	private XStreamOperator operator; 
	private Number operatorValue; 
	
	public XStreamCriteriaType getType() {
		return type;
	}
	public void setType(XStreamCriteriaType type) {
		this.type = type;
	}
	public XStreamRowValueModel getValue1() {
		return value1;
	}
	public void setValue1(XStreamRowValueModel value1) {
		this.value1 = value1;
	}
	public XStreamRowValueModel getValue2() {
		return value2;
	}
	public void setValue2(XStreamRowValueModel value2) {
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
