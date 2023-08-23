package com.dunkware.trade.service.stream.json.worker.scanner;

import java.util.Map;

import com.dunkware.common.util.datagrid.DataGridUpdateType;

public class StreamEntityScannerUpdate {
	
	public DataGridUpdateType type;
	private String ident; 
	private Map<String,Object> fields;
	
	public DataGridUpdateType getType() {
		return type;
	}
	public void setType(DataGridUpdateType type) {
		this.type = type;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public Map<String, Object> getFields() {
		return fields;
	}
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}
	
	
	

}
