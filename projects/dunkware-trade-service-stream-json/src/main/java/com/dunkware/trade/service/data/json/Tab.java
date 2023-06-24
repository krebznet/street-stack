package com.dunkware.trade.service.data.json;

import java.util.ArrayList;
import java.util.List;

public class Tab {
	
	private String name; 
	private List<Widget> widgets = new ArrayList<Widget>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	
	

}
