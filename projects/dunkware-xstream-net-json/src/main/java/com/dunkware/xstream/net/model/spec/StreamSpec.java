package com.dunkware.xstream.net.model.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.net.model.entity.Entity;
import com.dunkware.xstream.net.model.entity.EntityGroup;
import com.dunkware.xstream.net.model.entity.EntityType;

public class StreamSpec {
	
	private int id; 
	private String identifier; 
	private String name; 
	private double version; 
	private EntityType entityType;
	private List<EntityGroup> entityGroups = new ArrayList<EntityGroup>();
	private List<Entity> entities = new ArrayList<Entity>();
	private List<EntityField> entityFields = new ArrayList<EntityField>();
	private List<EntitySignal> entitySignals = new ArrayList<EntitySignal>();
	
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
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
	public List<EntityGroup> getEntityGroups() {
		return entityGroups;
	}
	public void setEntityGroups(List<EntityGroup> entityGroups) {
		this.entityGroups = entityGroups;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	public List<EntityField> getEntityFields() {
		return entityFields;
	}
	public void setEntityFields(List<EntityField> entityFields) {
		this.entityFields = entityFields;
	}
	public List<EntitySignal> getEntitySignals() {
		return entitySignals;
	}
	public void setEntitySignals(List<EntitySignal> entitySignals) {
		this.entitySignals = entitySignals;
	}

	
}
