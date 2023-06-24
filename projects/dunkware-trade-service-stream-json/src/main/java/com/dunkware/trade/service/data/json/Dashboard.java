package com.dunkware.trade.service.data.json;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {

	private String name; 
	private List<Tab> tabs = new ArrayList<Tab>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Tab> getTabs() {
		return tabs;
	}
	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}
	
	
}
