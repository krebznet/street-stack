package com.dunkware.trade.service.stream.server.web;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;

import ca.odell.glazedlists.ObservableElementList;

public interface StreamWebService {

	
	public ObservableElementList<StreamSessionNodeBean> getStreamNodes(String streamIdentifier) throws Exception; 
	
	public DExecutor getExecutor();
	
	
}
