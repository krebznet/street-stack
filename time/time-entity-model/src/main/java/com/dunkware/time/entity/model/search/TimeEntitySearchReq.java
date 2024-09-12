package com.dunkware.time.entity.model.search;

import java.util.List;

public class TimeEntitySearchReq {
	
	private String entityType;
    private List<TimeEntitySearchCriteria> criteria;

    // Constructors, getters, and setters

    public TimeEntitySearchReq() {}

    public TimeEntitySearchReq(String entityType, List<TimeEntitySearchCriteria> criteria) {
        this.entityType = entityType;
        this.criteria = criteria;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public List<TimeEntitySearchCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<TimeEntitySearchCriteria> criteria) {
        this.criteria = criteria;
    }

}
