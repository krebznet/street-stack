package com.dunkware.xstream.net.core.scanner;

import java.util.List;

public interface StreamEntityScannerListener {
	
	public void scannerUpdate(List<StreamEntityScannerRow> inserts, List<StreamEntityScannerRow> updates,List<StreamEntityScannerRow> deletes); 
	

}
