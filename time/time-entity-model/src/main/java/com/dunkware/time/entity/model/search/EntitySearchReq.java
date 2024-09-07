package com.dunkware.time.entity.model.search;

import java.util.List;

public class EntitySearchReq {
	
	private String entityType;
    private List<EntitySearchCriteria> criteria;

    // Constructors, getters, and setters

    public EntitySearchReq() {}

    public EntitySearchReq(String entityType, List<EntitySearchCriteria> criteria) {
        this.entityType = entityType;
        this.criteria = criteria;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public List<EntitySearchCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<EntitySearchCriteria> criteria) {
        this.criteria = criteria;
    }

}
