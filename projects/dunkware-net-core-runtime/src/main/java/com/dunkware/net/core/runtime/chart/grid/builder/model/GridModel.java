package com.dunkware.net.core.runtime.chart.grid.builder.model;

import java.util.ArrayList;
import java.util.List;


public class GridModel {
	
	private List<GridModelColumn> columns = new ArrayList<GridModelColumn>();
	private boolean pivot = false; 
	private boolean streaming = false; 
	private String data;
	private String rowNodeId = null;
	private int id;
	public List<GridModelColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<GridModelColumn> columns) {
		this.columns = columns;
	}
	public boolean isPivot() {
		return pivot;
	}
	public void setPivot(boolean pivot) {
		this.pivot = pivot;
	}
	public boolean isStreaming() {
		return streaming;
	}
	public void setStreaming(boolean streaming) {
		this.streaming = streaming;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRowNodeId() {
		return rowNodeId;
	}
	public void setRowNodeId(String rowNodeId) {
		this.rowNodeId = rowNodeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	
	
	
	
	
	
	
}
