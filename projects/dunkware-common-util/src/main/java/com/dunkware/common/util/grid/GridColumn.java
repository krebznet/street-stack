package com.dunkware.common.util.grid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "format" })
public class GridColumn {

	private Object field; 
	private String headerName; 
	@JsonIgnore()
	private GridFormat format;
	private boolean hide = false; 
	
	
	
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
	public GridFormat getFormat() {
		return format;
	}
	public void setFormat(GridFormat format) {
		this.format = format;
	}
	
	
	
}
