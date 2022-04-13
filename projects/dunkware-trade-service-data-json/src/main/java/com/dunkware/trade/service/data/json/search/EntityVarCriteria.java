package com.dunkware.trade.service.data.json.search;

import com.dunkware.common.util.enums.DOperator;

public class EntityVarCriteria {
	
	private String identifier; 
	private DOperator operator; 
	private Double value;
	
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public DOperator getOperator() {
		return operator;
	}
	public void setOperator(DOperator operator) {
		this.operator = operator;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	} 
	
	

}
