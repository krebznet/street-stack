
package com.dunkware.trade.service.stream.worker.session.container.channels;

import com.dunkware.common.util.datagrid.DataGridConsumer;
import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.xstream.api.XStreamEntityScanner;
import com.dunkware.xstream.api.XStreamEntityScannerException;
import com.dunkware.xstream.model.scanner.XStreamEntityScannerModel;

public class StreamWorkerEntityScanner implements DataGridConsumer {
	
	private XStreamEntityScanner entityScanner;
	
	public void start(XStreamEntityScannerModel model) throws XStreamEntityScannerException { 
		
	}

	@Override
	public void consumeUpdate(DataGridUpdate updates) {
		// should wrap this up and send it through the channel
		// StreamWorkerEntityScannerUpdate - getUpdates();
		
	}
	

}
