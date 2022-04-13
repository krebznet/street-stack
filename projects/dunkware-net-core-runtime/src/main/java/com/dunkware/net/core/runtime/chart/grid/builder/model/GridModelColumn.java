package com.dunkware.net.core.runtime.chart.grid.builder.model;

import java.util.ArrayList;
import java.util.List;


public class GridModelColumn {
	
	private String field;
	// optional
	private String headerName = null;
	// optional styling we can create custom
	private String cellClass = null;
	
	private boolean rowDrag = true; 
	private boolean sortable = true;
	private boolean filter = true;
	private GridModelValueParser valueParser = null; 
	
	private List<String> cellClassRules = new ArrayList<String>();
	
	public GridModelColumn(String field) { 
		this.field = field;
	}
	public void validate() throws Exception { 
		
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getCellClass() {
		return cellClass;
	}
	public void setCellClass(String cellClass) {
		this.cellClass = cellClass;
	}
	public boolean isRowDrag() {
		return rowDrag;
	}
	public void setRowDrag(boolean rowDrag) {
		this.rowDrag = rowDrag;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public boolean isFilter() {
		return filter;
	}
	public void setFilter(boolean filter) {
		this.filter = filter;
	}
	public GridModelValueParser getValueParser() {
		return valueParser;
	}
	public void setValueParser(GridModelValueParser valueParser) {
		this.valueParser = valueParser;
	}
	public List<String> getCellClassRules() {
		return cellClassRules;
	}
	public void setCellClassRules(List<String> cellClassRules) {
		this.cellClassRules = cellClassRules;
	}
	
	


	
	
}
