package com.dunkware.common.util.grid.rules;

import com.dunkware.common.util.grid.GridOperator;

public class GridRowRule {
	
	private String field; 
	private GridOperator operator; 
	private Object value;
	
	public GridOperator getOperator() {
		return operator;
	}
	public void setOperator(GridOperator operator) {
		this.operator = operator;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	} 
	
	
	
	

}
