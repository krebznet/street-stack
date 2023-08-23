package com.dunkware.trade.service.stream.server.scanner;

import java.util.Map;

import com.dunkware.common.util.datagrid.DataGridUpdateType;

public class StreamEntityScannerUpdate {

	public DataGridUpdateType type;
	private int id; 
	private String ident;
	private Map<String,Object> fields;
	
	public DataGridUpdateType getType() {
		return type;
	}
	public void setType(DataGridUpdateType type) {
		this.type = type;
	}
	
	public Map<String, Object> getFields() {
		return fields;
	}
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	
	
	
}
