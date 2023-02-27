package com.dunkware.xstream.model.spec;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.model.entity.Entity;
import com.dunkware.xstream.model.entity.EntityGroup;
import com.dunkware.xstream.model.entity.EntityType;

public class StreamSpec {
	
	private int id; 
	private String identifier; 
	private String name; 
	private double version; 
	private EntityType entityType;
	private List<EntityGroup> entityGroups = new ArrayList<EntityGroup>();
	private List<Entity> entities = new ArrayList<Entity>();
	private List<StreamSpecEntityField> entityFields = new ArrayList<StreamSpecEntityField>();
	private List<StreamSpecEntitySignal> entitySignals = new ArrayList<StreamSpecEntitySignal>();
	private String eventBrokers; 
	private DTimeZone timeZone; 
	
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
	public List<StreamSpecEntityField> getEntityFields() {
		return entityFields;
	}
	public void setEntityFields(List<StreamSpecEntityField> entityFields) {
		this.entityFields = entityFields;
	}
	public List<StreamSpecEntitySignal> getEntitySignals() {
		return entitySignals;
	}
	public void setEntitySignals(List<StreamSpecEntitySignal> entitySignals) {
		this.entitySignals = entitySignals;
	}
	public String getEventBrokers() {
		return eventBrokers;
	}
	public void setEventBrokers(String eventBrokers) {
		this.eventBrokers = eventBrokers;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	
	
	

	
}
