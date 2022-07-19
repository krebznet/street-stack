package com.dunkware.xstream.model.search;

public class SessionEntityScanner {
	
	private SessionEntitySearch search; 
	private int scanInterval; 
	private int scannerId;
	
	
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
	public int getScannerId() {
		return scannerId;
	}
	public void setScannerId(int scannerId) {
		this.scannerId = scannerId;
	} 
	
	
	

}
