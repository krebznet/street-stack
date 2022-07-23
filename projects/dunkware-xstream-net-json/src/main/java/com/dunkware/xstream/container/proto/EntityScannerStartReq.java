package com.dunkware.xstream.container.proto;

import java.util.List;

import com.dunkware.xstream.model.search.SessionEntitySearch;

public class EntityScannerStartReq {
	
	private SessionEntitySearch search; 
	private List<String> scannerVars;
	
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	}
	public List<String> getScannerVars() {
		return scannerVars;
	}
	public void setScannerVars(List<String> scannerVars) {
		this.scannerVars = scannerVars;
	}
	
	

}
