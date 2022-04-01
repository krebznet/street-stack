package com.dunkware.trade.service.data.json.search;

import com.dunkware.trade.service.data.json.enums.Operator;

public class EntityVarCriteria {
	
	private String identifier; 
	private Operator operator; 
	private Double value;
	
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	} 
	
	

}
