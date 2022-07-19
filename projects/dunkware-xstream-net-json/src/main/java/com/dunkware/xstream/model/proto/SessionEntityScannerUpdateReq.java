package com.dunkware.xstream.model.proto;

import com.dunkware.xstream.model.search.SessionEntityScanner;

public class SessionEntityScannerUpdateReq {
	
	private SessionEntityScanner scanner; 
	private String scannerId;
	public SessionEntityScanner getScanner() {
		return scanner;
	}
	public void setScanner(SessionEntityScanner scanner) {
		this.scanner = scanner;
	}
	public String getScannerId() {
		return scannerId;
	}
	public void setScannerId(String scannerId) {
		this.scannerId = scannerId;
	} 
	
	

}
