package com.dunkware.time.entity.mod.entity;


import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;


@Table(name = "entity")
public class DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityType; // Referencing entity type name

    @ElementCollection
    @CollectionTable(name = "entity_properties", joinColumns = @JoinColumn(name = "entity_id"))
    @MapKeyColumn(name = "property_name")
    @Column(name = "property_value")
    private Map<String, String> properties = new HashMap<>();
    private int version;

    // Constructors, getters, and setters
    public DBEntity() {}

    public DBEntity(String entityType, Map<String, String> properties) {
        this.entityType = entityType;
        this.properties = properties;
    }

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

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
    
    
}