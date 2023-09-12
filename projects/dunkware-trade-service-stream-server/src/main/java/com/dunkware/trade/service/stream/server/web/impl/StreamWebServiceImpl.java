package com.dunkware.trade.service.stream.server.web.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.spring.runtime.services.ExecutorService;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeBean;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;
import com.dunkware.trade.service.stream.server.web.StreamWebService;

import ca.odell.glazedlists.ObservableElementList;

@Service
public class StreamWebServiceImpl implements StreamWebService  {

	
	@Autowired
	private StreamControllerService streamService; 
	
	@Autowired
	private ExecutorService executorService; 
	
	@Override
	public ObservableElementList<StreamSessionNodeBean> getStreamNodes(String streamIdentifier) throws Exception {
		ObservableElementList<StreamSessionNodeBean> elements = streamService.getStreamByName(streamIdentifier).getSessionNodeBeans();
		return elements;
	}
	
	public DExecutor getExecutor() { 
		return executorService.get();
	}

	
}
