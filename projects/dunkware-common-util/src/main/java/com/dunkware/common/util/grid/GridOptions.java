package com.dunkware.common.util.grid;

import java.util.ArrayList;
import java.util.List;

public class GridOptions {
	
	private List<GridColumn> columns = new ArrayList<GridColumn>();
	private Number id;
	
	public List<GridColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	
	
	
	

}
