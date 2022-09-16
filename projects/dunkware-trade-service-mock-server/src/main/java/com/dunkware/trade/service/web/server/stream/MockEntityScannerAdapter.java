package com.dunkware.trade.service.web.server.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.data.NetBean;
import com.dunkware.common.util.data.NetScanner;
import com.dunkware.common.util.data.NetScannerDelta;
import com.dunkware.common.util.data.NetScannerDeltaWeb;
import com.dunkware.common.util.data.NetScannerWatcher;
import com.dunkware.common.util.json.DJson;
import com.dunkware.spring.streaming.StreamingAdapter;

public class MockEntityScannerAdapter implements NetScannerWatcher {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private MockEntityScanner scanner; 
	private StreamingAdapter adapter; 
	private boolean initailized = false; 
	
	public void start(MockEntityScanner scanner, StreamingAdapter adapter) { 
		this.scanner = scanner;
		this.adapter = adapter; 
		scanner.getScanner().addWatcher(this);
	}
	@Override
	public void scannerDelta(NetScanner scanner, NetScannerDelta delta) {
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
			 	updates.clear();
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
	
	public void stop() { 
		scanner.getScanner().removeWatcher(this);
	}
	
	

}
