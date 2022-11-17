package com.dunkware.xstream.model.entity;

public class EntityTypeProperty {

	private int id; 
	private String identifier; 
	private String name; 
	private EntityPropertyType type;
	
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
	public EntityPropertyType getType() {
		return type;
	}
	public void setType(EntityPropertyType type) {
		this.type = type;
	} 
	
	
}
