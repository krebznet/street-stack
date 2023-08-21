package com.dunkware.common.util.datagrid;

import com.dunkware.common.util.observable.ObservableBean;

public class DataGridBean extends ObservableBean {
	
	private int id; 
	private String status; 
	private int size; 
	private int filled; 
	private String symbol;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getFilled() {
		return filled;
	}
	public void setFilled(int filled) {
		this.filled = filled;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	} 
	
	

}
