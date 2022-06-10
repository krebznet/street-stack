package com.dunkware.xstream.net.protocol.data;

import java.util.ArrayList;
import java.util.List;

public class TableStream {
	
	private int id; 
	private List<TableStreamRow>  rows = new ArrayList<TableStreamRow>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<TableStreamRow> getRows() {
		return rows;
	}
	public void setRows(List<TableStreamRow> rows) {
		this.rows = rows;
	}
	
	

}
