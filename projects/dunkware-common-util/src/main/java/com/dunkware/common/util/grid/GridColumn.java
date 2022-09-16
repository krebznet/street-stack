package com.dunkware.common.util.grid;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.grid.action.GridAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "format" })
public class GridColumn {

	private Object field; 
	private String headerName; 
	@JsonIgnore()
	private String valueFormatter = null;
	private boolean hide = false;
	private List<GridAction> actions = new ArrayList<GridAction>();
	
	

	public Object getField() {
		return field;
	}
	public void setField(Object field) {
		this.field = field;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	
	public boolean isHide() {
		return hide;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	public String getValueFormatter() {
		return valueFormatter;
	}
	
	public void setValueFormatter(String valueFormatter) {
		this.valueFormatter = valueFormatter;
	}
	public List<GridAction> getActions() {
		return actions;
	}
	public void setActions(List<GridAction> actions) {
		this.actions = actions;
	}
	
	
	
	
	
	
	
	
	
	
}
