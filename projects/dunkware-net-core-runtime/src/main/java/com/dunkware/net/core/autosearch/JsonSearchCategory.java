package com.dunkware.net.core.autosearch;

import java.util.ArrayList;
import java.util.List;

public class JsonSearchCategory {

	private String categoryName; 
	private String categoryType;
	
	private List<JsonSearchElement> elements = new ArrayList<JsonSearchElement>();
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public List<JsonSearchElement> getElements() {
		return elements;
	}
	public void setElements(List<JsonSearchElement> elements) {
		this.elements = elements;
	} 
	
	
	
}
