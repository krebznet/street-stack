package com.dunkware.xstream.model.proto;

import com.dunkware.xstream.model.scanner.SessionEntityScanner;

public class SessionEntityScannerStartReq {
	
	private SessionEntityScanner scanner; 
	private String streamIdentifier;
	
	public SessionEntityScanner getScanner() {
		return scanner;
	}
	public void setScanner(SessionEntityScanner scanner) {
		this.scanner = scanner;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	} 
	
	

}
