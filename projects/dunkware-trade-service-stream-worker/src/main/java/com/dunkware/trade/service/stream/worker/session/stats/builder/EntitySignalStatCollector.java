package com.dunkware.trade.service.stream.worker.session.stats.builder;

import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamRowSignal;

public class EntitySignalStatCollector implements XStreamEntityListener {
	
	public void init(XStreamEntity entity) { 
			entity.addRowListener(this);
	}

	@Override
	public void rowSignal(XStreamEntity row, XStreamRowSignal signal) {
		// TODO Auto-generated method stub
		
	}
	
	

}
