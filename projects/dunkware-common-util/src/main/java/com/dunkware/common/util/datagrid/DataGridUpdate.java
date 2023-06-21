package com.dunkware.common.util.datagrid;

import com.dunkware.common.util.json.DJson;

public class DataGridUpdate {
	
	
	private Object json; 
	private Number id; 
	private String type;
	
	
	
	public static void main(String[] args) {
		DataGridUpdate up = new DataGridUpdate();
		up.id = 23;
		up.type = "DELETE";
		try {
			System.out.println(DJson.serializePretty(up));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
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
