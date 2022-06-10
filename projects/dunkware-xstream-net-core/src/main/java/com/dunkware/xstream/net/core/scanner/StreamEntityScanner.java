package com.dunkware.xstream.net.core.scanner;

import java.util.Collection;

import com.dunkware.xstream.net.core.container.ContainerSearchException;
import com.dunkware.xstream.net.model.search.SessionEntitySearch;

public interface StreamEntityScanner {

	public void addListener(StreamEntityScannerListener listener);
	
	public void removeListener(StreamEntityScannerListener listener);
	
	public Collection<StreamEntityScannerRow> getRows();
	
	public void update(SessionEntitySearch search) throws ContainerSearchException;
	
	public void stop(); 
	
	public int scannerId();
}
