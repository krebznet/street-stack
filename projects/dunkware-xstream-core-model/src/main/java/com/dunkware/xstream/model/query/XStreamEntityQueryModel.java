package com.dunkware.xstream.model.query;

import java.util.ArrayList;
import java.util.List;

public class XStreamEntityQueryModel {

	private List<XStreamCriteriaModel> criterias = new ArrayList<XStreamCriteriaModel>();

	public List<XStreamCriteriaModel> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<XStreamCriteriaModel> criterias) {
		this.criterias = criterias;
	}
	
	
}
