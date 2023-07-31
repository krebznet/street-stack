package com.dunkware.xstream.api;

public interface XStreamEntityScannerListener {

	public void scannerUpdate(XStreamRow row);
	
	public void scannerInsert(XStreamRow row);
	
	public void scannerRemove(XStreamRow row);
}
