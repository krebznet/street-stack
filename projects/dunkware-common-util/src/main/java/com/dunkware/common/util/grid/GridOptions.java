package com.dunkware.common.util.grid;

import java.util.ArrayList;
import java.util.List;

public class GridOptions {
	
	private String rowSelection = "single";
	private boolean pagination = true;
	private int paginzationSize = 50; 
	
	private List<GridColumn> columns = new ArrayList<GridColumn>();

	
	public List<GridColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}
	public String getRowSelection() {
		return rowSelection;
	}
	public void setRowSelection(String rowSelection) {
		this.rowSelection = rowSelection;
	}
	public boolean isPagination() {
		return pagination;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public int getPaginzationSize() {
		return paginzationSize;
	}
	public void setPaginzationSize(int paginzationSize) {
		this.paginzationSize = paginzationSize;
	}
	
	
	
	
	

}
