package com.dunkware.xstream.api;

import java.util.List;

public interface XStreamEntityScanner {
	
	void dispose();
	
	void setVars(List<String> varIdentifiers);

	void addListener(XStreamEntityScannerListener listener);
	
	void removeListener(XStreamEntityScannerListener listener);
	
	 
}
