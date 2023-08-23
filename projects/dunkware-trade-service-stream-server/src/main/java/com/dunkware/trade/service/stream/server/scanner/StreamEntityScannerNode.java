package com.dunkware.trade.service.stream.server.scanner;

import java.util.List;

import com.dunkware.spring.cluster.DunkNetChannel;
import com.dunkware.spring.cluster.anot.ADunkNetEvent;

public class StreamEntityScannerNode {
	
	private DunkNetChannel channel; 
	
	private StreamEntityScanner scanner; 
	
	@ADunkNetEvent
	private void scannerUpdates(List<StreamEntityScannerUpdate> updates) {
		
	}

}
