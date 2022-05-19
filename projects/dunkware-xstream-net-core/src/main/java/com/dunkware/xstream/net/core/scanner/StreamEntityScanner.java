package com.dunkware.xstream.net.core.scanner;

import java.util.Collection;

public interface StreamEntityScanner {

	public void addListener(StreamEntityScannerListener listener);
	
	public void removeListener(StreamEntityScannerListener listener);
	
	public Collection<StreamEntityScannerItem> getItems();
	
	public void dispose(); 
	
	public String getIdentifier();
}
