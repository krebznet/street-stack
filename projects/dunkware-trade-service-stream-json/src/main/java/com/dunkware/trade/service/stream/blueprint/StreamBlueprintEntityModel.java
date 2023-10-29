package com.dunkware.trade.service.stream.blueprint;

import java.util.ArrayList;
import java.util.List;

public class StreamBlueprintEntityModel {
	
	private long id; 
	private String identifier; 
	private String name; 
	private List<String> tags = new ArrayList<String>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	

}
