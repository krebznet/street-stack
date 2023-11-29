package com.dunkware.stream.cluster.proto.controller.beans;

import com.dunkware.common.util.observable.ObservableBean;

public class StreamEntityVarBean extends ObservableBean {

	private int id; 
	private String identifier; 
	private String name; 
	private String group;
	private Number value; 
	private String format;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Number getValue() {
		return value;
	}
	public void setValue(Number value) {
		this.value = value;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	} 
	
	
}
