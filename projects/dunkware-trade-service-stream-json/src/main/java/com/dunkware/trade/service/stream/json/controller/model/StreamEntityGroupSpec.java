package com.dunkware.trade.service.stream.json.controller.model;

import java.util.ArrayList;
import java.util.List;

public class StreamEntityGroupSpec {
	
	private String identifier; 
	private int id; 
	private String streamIdentifier; 
	private List<StreamEntitySpec> entities = new ArrayList<StreamEntitySpec>();
	
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public List<StreamEntitySpec> getEntities() {
		return entities;
	}
	public void setEntities(List<StreamEntitySpec> entities) {
		this.entities = entities;
	}
	
	
	

}
