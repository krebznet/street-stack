package com.dunkware.trade.service.stream.server.controller.session.container.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetBean;
import com.dunkware.common.util.data.NetScannerDelta;
import com.dunkware.common.util.data.NetScannerDeltaWeb;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.server.streaming.StreamingAdapter;
import com.dunkware.trade.service.stream.server.streaming.StreamingListener;

public class SessionContainerEntityScannerStream implements SessionContainerEntityScannerListener, StreamingListener {
	
	private SessionContainerEntityScanner scanner;
	private StreamingAdapter adapter; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean disposed = false; 
	private boolean initailized = false; 
	
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
			List<Map<String,Object>> inserts = new ArrayList<Map<String,Object>>();
			List<Object> deletes = new ArrayList<Object>();
			List<Map<String,Object>> updates = new ArrayList<Map<String,Object>>();
			
			for (NetBean bean : delta.getInserts()) {
				inserts.add(bean.getValues());
			}
			
			for (NetBean bean : delta.getUpdates()) {
				updates.add(bean.getValues());
			}
			
			for (Object object : delta.getDeletes()) {
				deletes.add(object);
			
			}
			if(!initailized) {
				inserts.addAll(updates);
				initailized = true;
			}
			if(deletes.size() > 0)
			web.setDeletes(deletes);
			if(inserts.size() > 0)
			web.setInserts(inserts);
			if(updates.size() > 0)
			web.setUpdates(updates);
			
			
			
			System.out.println(DJson.serialize(web));
			
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
