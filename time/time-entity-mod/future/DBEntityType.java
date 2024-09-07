package com.dunkware.time.entity.mod.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "entity_type")
public class DBEntityType {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true, nullable = false)
	    private String name;

	    @OneToMany(mappedBy = "entityType", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<DBEntityTypeAttribute> attributes = new ArrayList<>();

	    private int version;

	    // Constructors, getters, and setters
	    public DBEntityType() {}

	    public DBEntityType(String name, List<DBEntityTypeAttribute> attributes, int version) {
	        this.name = name;
	        this.attributes = attributes;
	        this.version = version;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<DBEntityTypeAttribute> getAttributes() {
	        return attributes;
	    }

	    public void setAttributes(List<DBEntityTypeAttribute> attributes) {
	        this.attributes = attributes;
	        for (DBEntityTypeAttribute attribute : attributes) {
	            attribute.setEntityType(this);
	        }
	    }

	    public int getVersion() {
	        return version;
	    }

	    public void setVersion(int version) {
	        this.version = version;
	    }

	    public void incrementVersion() {
	        this.version++;
	    }

}
