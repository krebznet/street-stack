package com.dunkware.xstream.model.entity.query.type;

import java.util.ArrayList;
import java.util.List;

public class XStreamEntityQueryType {

	private List<XStreamEntityCriteriaType> criterias = new ArrayList<XStreamEntityCriteriaType>();

	public List<XStreamEntityCriteriaType> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<XStreamEntityCriteriaType> criterias) {
		this.criterias = criterias;
	}
	
	
}
