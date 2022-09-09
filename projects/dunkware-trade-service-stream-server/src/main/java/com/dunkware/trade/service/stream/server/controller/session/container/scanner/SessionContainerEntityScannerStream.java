package com.dunkware.trade.service.stream.server.controller.session.container.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetBean;
import com.dunkware.common.util.data.NetScannerDelta;
import com.dunkware.common.util.data.NetScannerDeltaWeb;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.trade.service.stream.server.streaming.StreamingListener;

public class SessionContainerEntityScannerStream implements SessionContainerEntityScannerListener, StreamingListener {
	
	private SessionContainerEntityScanner scanner;
	private StreamingAdapter adapter; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean disposed = false; 
	
	public void start(SessionContainerEntityScanner scanner, StreamingAdapter adapter) { 
		this.scanner = scanner; 
		this.adapter = adapter; 
		scanner.addListener(this);
		adapter.addListener(this);
	}


	@Override
	public void scannerDelta(NetScannerDelta delta) {
		try {
			NetScannerDeltaWeb web = new NetScannerDeltaWeb();
			if(delta.getDeletes().size() > 0) {
				web.setDeletes(delta.getDeletes().toArray(new NetBean[delta.getDeletes().size()]));
			}
			if(delta.getInserts().size() > 0) {
				web.setInserts(delta.getInserts().toArray(new NetBean[delta.getInserts().size()]));
			}
			if(delta.getUpdates().size() > 0) { 
				web.setUpdates(delta.getUpdates().toArray(new NetBean[delta.getUpdates().size()]));
			}
			
			adapter.send(web);
		} catch (Exception e) {
			logger.error("Error sending scanner delta streaming adapter we shoul dhav handled closed");;
		}
	}


	@Override
	public void clientDisconnect(StreamingAdapter adapter) {
		if(!disposed) { 
			scanner.removeListener(this);
			disposed = true; 
			scanner.dispose();
		}
	}


	@Override
	public void serverDisconnect(StreamingAdapter adapter) {
		if(!disposed) { 
			scanner.removeListener(this);
			disposed = true; 
			scanner.dispose();
		}
	}
	
	
	
	
	

}
