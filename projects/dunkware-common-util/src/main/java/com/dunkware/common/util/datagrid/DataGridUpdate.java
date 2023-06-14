package com.dunkware.common.util.datagrid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dunkware.common.util.json.DJson;

public class DataGridUpdate {
	
	
	private String data; 
	private Number rowId; 
	private String type;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public Number getRowId() {
		return rowId;
	}
	public void setRowId(Number rowId) {
		this.rowId = rowId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
