package com.dunkware.net.core.runtime.chart.grid.builder.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class GridJson {
	
	private GridJsonColumnDef[] columnDefs;
	private String data;
	private boolean pivot = true;
	public GridJsonColumnDef[] getColumnDefs() {
		return columnDefs;
	}
	public void setColumnDefs(GridJsonColumnDef[] columnDefs) {
		this.columnDefs = columnDefs;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isPivot() {
		return pivot;
	}
	public void setPivot(boolean pivot) {
		this.pivot = pivot;
	} 
	
	

}
