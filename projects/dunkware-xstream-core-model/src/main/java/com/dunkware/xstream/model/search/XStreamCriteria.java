package com.dunkware.xstream.model.search;

public class XStreamCriteria {
	
	private XStreamCriteriaType type;
	private XStreamValue value1; 
	private XStreamValue value2;
	private XStreamCriteriaCompareFunc compareFunc; 
	
	public XStreamCriteriaType getType() {
		return type;
	}
	public void setType(XStreamCriteriaType type) {
		this.type = type;
	}
	public XStreamValue getValue1() {
		return value1;
	}
	public void setValue1(XStreamValue value1) {
		this.value1 = value1;
	}
	public XStreamValue getValue2() {
		return value2;
	}
	public void setValue2(XStreamValue value2) {
		this.value2 = value2;
	}
	public XStreamCriteriaCompareFunc getCompareFunc() {
		return compareFunc;
	}
	public void setCompareFunc(XStreamCriteriaCompareFunc compareFunc) {
		this.compareFunc = compareFunc;
	} 
	
	
	
	

}
