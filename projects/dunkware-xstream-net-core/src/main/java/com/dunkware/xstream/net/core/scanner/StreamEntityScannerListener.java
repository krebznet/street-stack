package com.dunkware.xstream.net.core.scanner;

import java.util.List;

public interface StreamEntityScannerListener {
	
	public void scannerUpdate(List<StreamEntityScannerItem> inserts, List<StreamEntityScannerItem> updates,List<StreamEntityScannerItem> deletes); 
	

}
