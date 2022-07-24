package com.dunkware.xstream.container.proto;

import java.util.List;

import com.dunkware.xstream.model.scanner.SessionEntityScanner;

public class EntityScannerStartReq {
	
	private SessionEntityScanner scanner; 
	private List<String> vars;
	
	public SessionEntityScanner getScanner() {
		return scanner;
	}
	public void setScanner(SessionEntityScanner scanner) {
		this.scanner = scanner;
	}
	public List<String> getVars() {
		return vars;
	}
	public void setVars(List<String> vars) {
		this.vars = vars;
	}
	
	
	
	

}
