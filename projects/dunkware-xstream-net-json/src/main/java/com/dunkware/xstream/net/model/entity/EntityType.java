package com.dunkware.xstream.net.model.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityType {
	
	private int id; 
	private String identifier; 
	private String name; 
	private List<EntityPropertyType> properties = new ArrayList<EntityPropertyType>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<EntityPropertyType> getProperties() {
		return properties;
	}
	public void setProperties(List<EntityPropertyType> properties) {
		this.properties = properties;
	}
	
	

}
