package com.dunkware.net.chart.grid.builder.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class GridJsonColumnDef {

	private String field; 
	private int maxWidth = -1;
	private boolean filter = true;
	private boolean sortable = true;
	private boolean rowDrag = false;
	private String cellRenderer = null;
	private String headerName = null;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
	public boolean isFilter() {
		return filter;
	}
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public boolean isRowDrag() {
		return rowDrag;
	}
	public void setRowDrag(boolean rowDrag) {
		this.rowDrag = rowDrag;
	}
	public String getCellRenderer() {
		return cellRenderer;
	}
	public void setCellRenderer(String cellRenderer) {
		this.cellRenderer = cellRenderer;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	
	
}
