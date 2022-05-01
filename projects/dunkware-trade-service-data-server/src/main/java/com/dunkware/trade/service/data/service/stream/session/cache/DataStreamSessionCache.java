package com.dunkware.trade.service.data.service.stream.session.cache;

import com.dunkware.trade.service.data.service.stream.session.DataStreamSession;

public interface DataStreamSessionCache {
	
	public void start(DataStreamSession session) throws Exception;
	
	public void dispose();
	

}
