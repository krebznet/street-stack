package com.dunkware.xstream.net.core.scanner;

import java.util.Collection;

import com.dunkware.xstream.model.search.SessionEntitySearch;
import com.dunkware.xstream.net.core.container.ContainerSearchException;

public interface StreamEntityScanner {

	public void addListener(StreamEntityScannerListener listener);
	
	public void removeListener(StreamEntityScannerListener listener);
	
	public Collection<StreamEntityScannerRow> getRows();
	
	public void update(SessionEntitySearch search) throws ContainerSearchException;
	
	public void stop(); 
	
	public int scannerId();
}
