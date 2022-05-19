package com.dunkware.trade.service.data.service.stream.cache.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.data.service.stream.cache.DataStreamSessionCache;
import com.dunkware.trade.service.data.service.stream.cache.DataStreamSessionCacheNode;
import com.dunkware.trade.service.data.service.stream.session.DataStreamSession;

public class DataStreamSessionCacheImpl implements DataStreamSessionCache {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private DataStreamSession session;
	
	private List<DataStreamSessionCacheNode> nodes;
	@Override
	public void start(DataStreamSession session) throws Exception {
		this.session = session; 
		
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
