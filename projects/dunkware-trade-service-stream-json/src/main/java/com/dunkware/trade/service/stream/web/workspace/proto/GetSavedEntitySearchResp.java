package com.dunkware.trade.service.stream.web.workspace.proto;

import com.dunkware.trade.service.stream.web.workspace.SavedSessionEntitySearch;

public class GetSavedEntitySearchResp {
	
	private SavedSessionEntitySearch search; 
	private String error;
	
	public SavedSessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SavedSessionEntitySearch search) {
		this.search = search;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	} 
	
	

}
