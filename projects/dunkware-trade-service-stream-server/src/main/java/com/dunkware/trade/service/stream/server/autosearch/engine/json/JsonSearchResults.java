package com.dunkware.trade.service.stream.server.autosearch.engine.json;

import java.util.ArrayList;
import java.util.List;

public class JsonSearchResults {
	
	private List<JsonSearchCategory> categories = new ArrayList<JsonSearchCategory>();

	public List<JsonSearchCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<JsonSearchCategory> categories) {
		this.categories = categories;
	}

	
}
