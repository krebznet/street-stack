package com.dunkware.time.entity.model.data;

import java.util.Map;

public class Entity {
    private Long id; // Unique identifier for the entity
    private String entityType; // Type of the entity, e.g., "Stock"
    private Map<String, Object> properties; // Dynamic properties
    private int version;
    
    
    // Constructors
    public Entity() {}

    public Entity(Long id, String entityType, Map<String, Object> properties) {
        this.id = id;
        this.entityType = entityType;
        this.properties = properties;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
    
    
}