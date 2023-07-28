package com.dunkware.trade.service.stream.json.query;

import java.util.ArrayList;
import java.util.List;

public class WebStreamCriteria {
	
	private String type; 
	private String compareFunction; 
	private String condition; 
	private double conditionValue;
	
	private List<WebStreamCriteriaValue> value = new ArrayList<WebStreamCriteriaValue>();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCompareFunction() {
		return compareFunction;
	}
	public void setCompareFunction(String compareFunction) {
		this.compareFunction = compareFunction;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public double getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(double conditionValue) {
		this.conditionValue = conditionValue;
	}
	public List<WebStreamCriteriaValue> getValue() {
		return value;
	}
	public void setValue(List<WebStreamCriteriaValue> value) {
		this.value = value;
	}
	
	
}
