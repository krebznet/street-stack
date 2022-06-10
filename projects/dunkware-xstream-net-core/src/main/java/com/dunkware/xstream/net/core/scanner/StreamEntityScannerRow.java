package com.dunkware.xstream.net.core.scanner;

import java.util.Map;

public interface StreamEntityScannerRow {
	
	public int getTimeInScanner();
	
	public int getEntityId();
	
	public String getEntityIdent();
	
	public Map<String,Object> getData();
	

}
