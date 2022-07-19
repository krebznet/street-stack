package com.dunkware.xstream.model.search;

public class SessionEntityScanner {
	
	private SessionEntitySearch search; 
	private int scanInterval; 
	private String scannerIdentifier; 
	
	public SessionEntitySearch getSearch() {
		return search;
	}
	public void setSearch(SessionEntitySearch search) {
		this.search = search;
	}
	public int getScanInterval() {
		return scanInterval;
	}
	public void setScanInterval(int scanInterval) {
		this.scanInterval = scanInterval;
	}
	public String getScannerIdentifier() {
		return scannerIdentifier;
	}
	public void setScannerIdentifier(String scannerIdentifier) {
		this.scannerIdentifier = scannerIdentifier;
	}
	
	
	

}
