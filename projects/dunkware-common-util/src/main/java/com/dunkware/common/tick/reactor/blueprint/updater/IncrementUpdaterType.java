package com.dunkware.common.tick.reactor.blueprint.updater;

import com.dunkware.common.tick.reactor.blueprint.ReactorUpdater;


public class IncrementUpdaterType extends ReactorUpdater  {
	
	private String value; 
	private String type;
	private String kind; 
	private String operator;
	private String fields;
	
	public IncrementUpdaterType() { 
		
	}

	
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


	public String getFields() {
		return fields;
	}


	public void setFields(String fields) {
		this.fields = fields;
	}
	
	
	
	
	



}
