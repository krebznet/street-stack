package com.dunkware.xstream.model.entity.query.type;

import java.util.ArrayList;
import java.util.List;

public class StreamEntityQueryType {

	private List<StreamEntityCriteriaType> criterias = new ArrayList<StreamEntityCriteriaType>();

	public List<StreamEntityCriteriaType> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<StreamEntityCriteriaType> criterias) {
		this.criterias = criterias;
	}
	
	
}
