package com.dunkware.utils.tick.reactor.blueprint;

import java.util.ArrayList;
import java.util.List;

public class ReactorTickSet {
	
	private String name; 
	private String prefix; 
	private int size;
	private int type;
	
	private List<ReactorTickField> fields = new ArrayList<ReactorTickField>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<ReactorTickField> getFields() {
		return fields;
	}
	public void setFields(List<ReactorTickField> fields) {
		this.fields = fields;
	} 
	
	
	
	
	

}
