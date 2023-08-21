package com.dunkware.xstream.api;

public interface XStreamEntityScannerListener {

	public void scannerUpdate(XStreamEntity row);
	
	public void scannerInsert(XStreamEntity row);
	
	public void scannerRemove(XStreamEntity row);
}
