package com.dunkware.trade.service.stream.json.query;

import java.util.ArrayList;
import java.util.List;

public class WebStreamQuery {

	private List<WebStreamCriteria> criterias = new ArrayList<WebStreamCriteria>();

	public List<WebStreamCriteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<WebStreamCriteria> criterias) {
		this.criterias = criterias;
	}
	
	
}
