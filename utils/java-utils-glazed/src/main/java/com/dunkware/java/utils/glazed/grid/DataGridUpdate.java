package com.dunkware.java.utils.glazed.grid;
//TODO: AVINASHANV-38 Data Grid Update
/**
 * this is the model feeding your ui data grid
 * has a generic object an id and a type (update,insert,delete)
 */
public class DataGridUpdate {
	
	
	private Object json; 
	private Number id; 
	private String type;
	
	
	
	
	public Object getJson() {
		return json;
	}
	public void setJson(Object json) {
		this.json = json;
	}
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
